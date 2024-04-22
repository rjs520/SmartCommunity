package com.rjs.smartcommunity.common.config;

import cn.hutool.core.util.ObjectUtil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rjs.smartcommunity.common.Constants;
import com.rjs.smartcommunity.common.enums.ResultCodeEnum;
import com.rjs.smartcommunity.common.enums.RoleEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.exception.CustomException;
import com.rjs.smartcommunity.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT拦截器组件
 *
 * @author rjs
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    /** 管理员服务对象，用于查询用户信息 */
    @Resource private AdminService adminService;

    /**
     * 在处理器（Controller方法）执行之前进行身份验证检查
     *
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param handler 处理器对象（Controller方法）
     * @return 如果验证通过，返回true；否则抛出异常或返回false
     * @throws CustomException 自定义异常，用于表示验证失败的情况
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 从HTTP请求的header中获取token
        String token = request.getHeader(Constants.TOKEN);
        if (ObjectUtil.isEmpty(token)) {
            // 如果没拿到，从参数里再拿一次
            token = request.getParameter(Constants.TOKEN);
        }

        // 2. 开始执行认证
        if (ObjectUtil.isEmpty(token)) {
            log.error("【JWT拦截器】token为空，无法进行身份验证。");
            throw new CustomException(ResultCodeEnum.TOKEN_INVALID_ERROR);
        }

        Account account = null;
        try {
            // 解析token获取存储的数据
            String userRole = JWT.decode(token).getAudience().get(0);
            String userId = userRole.split("-")[0];
            String role = userRole.split("-")[1];

            // 根据userId查询数据库
            if (RoleEnum.ADMIN.name().equals(role)) {
                account = adminService.selectById(Integer.valueOf(userId));
            }
        } catch (Exception e) {
            log.error("【JWT拦截器】解析token失败，token可能已过期或无效。");
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }

        if (ObjectUtil.isNull(account)) {
            log.error("【JWT拦截器】用户不存在，无法进行身份验证。");
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        try {
            // 使用用户密码作为密钥，构建JWT验证器
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(account.getPassword())).build();

            // 验证token
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            log.error("【JWT拦截器】token验证失败，token可能已过期或无效。");
            throw new CustomException(ResultCodeEnum.TOKEN_CHECK_ERROR);
        }

        // 验证通过，返回true
        log.info("【JWT拦截器】token验证通过，用户ID：{}", account.getId());
        return true;
    }
}
