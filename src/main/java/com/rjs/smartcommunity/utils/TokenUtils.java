package com.rjs.smartcommunity.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.rjs.smartcommunity.common.Constants;
import com.rjs.smartcommunity.common.enums.RoleEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Token工具类，提供生成和解析Token的功能，以及获取当前登录用户信息的方法
 *
 * @author rjs
 */
@Component
public class TokenUtils {

    /** 日志记录器 */
    private static final Logger log = LoggerFactory.getLogger(TokenUtils.class);

    /** 静态引用的AdminService实例，用于在静态方法中获取用户信息 */
    private static AdminService staticAdminService;

    /** 注入的AdminService实例，用于初始化静态AdminService实例 */
    @Resource private AdminService adminService;

    /**
     * 生成Token
     *
     * @param data 用户ID和角色组成的字符串（如：userId-role）
     * @param sign 密钥，用于对Token进行签名
     * @return 生成的Token字符串
     */
    public static String createToken(String data, String sign) {
        return JWT.create()
                // 将 userId-role 保存到 token 里面,作为载荷
                .withAudience(data)
                // 2小时后token过期
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2))
                // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(sign));
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return 当前登录的用户对象（Account），若无有效Token或查询失败，则返回空Account对象
     */
    public static Account getCurrentUser() {
        try {
            // 从请求上下文中获取当前HttpServletRequest对象
            HttpServletRequest request =
                    ((ServletRequestAttributes)
                                    Objects.requireNonNull(
                                            RequestContextHolder.getRequestAttributes()))
                            .getRequest();

            // 从请求头中获取Token
            String token = request.getHeader(Constants.TOKEN);

            // 如果Token不为空，则解析并获取用户信息
            if (ObjectUtil.isNotEmpty(token)) {
                String userRole = JWT.decode(token).getAudience().get(0);
                // 获取用户id
                String userId = userRole.split("-")[0];
                // 获取角色
                String role = userRole.split("-")[1];

                // 如果角色为管理员，则通过AdminService查询用户信息
                if (RoleEnum.ADMIN.name().equals(role)) {
                    return staticAdminService.selectById(Integer.valueOf(userId));
                }
            }
        } catch (Exception e) {
            log.error("获取当前用户信息出错", e);
        }

        // 若无有效Token或查询失败，返回空Account对象
        return new Account();
    }

    /** 初始化方法，在Bean创建完成后执行，将注入的AdminService赋值给静态AdminService实例 */
    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
    }
}
