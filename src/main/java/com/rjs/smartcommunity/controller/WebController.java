package com.rjs.smartcommunity.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.common.enums.ResultCodeEnum;
import com.rjs.smartcommunity.common.enums.RoleEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.service.AdminService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 前端基础接口控制器
 *
 * @author rjs
 */
@RestController
public class WebController {

    /** 注入管理员服务 */
    @Resource private AdminService adminService;

    /**
     * 主页访问接口
     *
     * @return Result 对象，包含消息"访问成功"
     */
    @GetMapping("/")
    public Result hello() {
        return Result.success("访问成功");
    }

    /**
     * 用户登录接口
     *
     * @param account 登录账号信息对象（Account实体类）
     * @return Result 对象，包含登录成功的账号信息（若为管理员，则经过AdminService.login处理）；或错误信息（如参数缺失）
     */
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        if (ObjectUtil.isEmpty(account.getUsername())
                || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            account = adminService.login(account);
        }
        return Result.success(account);
    }

    /**
     * 用户注册接口
     *
     * @param account 注册账号信息对象（Account实体类）
     * @return Result 对象，表示注册操作成功；或错误信息（如参数缺失）
     */
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername())
                || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.register(account);
        }
        return Result.success();
    }

    /**
     * 修改用户密码接口
     *
     * @param account 包含用户名、原密码、新密码的账号信息对象（Account实体类）
     * @return Result 对象，表示修改密码操作成功；或错误信息（如参数缺失）
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername())
                || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        }
        return Result.success();
    }
}
