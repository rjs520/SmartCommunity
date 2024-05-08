package com.rjs.smartcommunity.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.common.enums.ResultCodeEnum;
import com.rjs.smartcommunity.common.enums.RoleEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.service.AdminService;
import com.rjs.smartcommunity.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 前端基础接口控制器
 *
 * @author rjs
 */
@Tag(name = "WebController", description = "前端基础接口控制器")
@RestController
public class WebController {

    /** 注入管理员服务 */
    @Resource private AdminService adminService;

    /** 注入用户服务 */
    @Resource private UserService userService;

    /**
     * 主页访问接口
     *
     * @return Result 对象，包含消息"访问成功"
     */
    @Operation(summary = "主页访问接口", description = "主页访问接口")
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "访问成功"),
                @ApiResponse(responseCode = "400", description = "参数异常")
            })
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
    @Operation(summary = "用户登录接口", description = "用户登录接口")
    @Parameter(name = "account", description = "登录账号信息对象（Account实体类）", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "成功"),
                @ApiResponse(responseCode = "4001", description = "参数缺失"),
                @ApiResponse(responseCode = "400", description = "参数异常"),
                @ApiResponse(responseCode = "5003", description = "账号或密码错误")
            })
    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        // 检查传入的登录请求参数是否完整（包含用户名、密码、角色）
        if (ObjectUtil.isEmpty(account.getUsername())
                || ObjectUtil.isEmpty(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            // 如果任一参数缺失，返回参数丢失错误
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }

        // 判断用户尝试以管理员身份登录
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            // 调用AdminService的login方法进行管理员登录验证，并获取登录后的Account对象
            account = adminService.login(account);
        } else if (RoleEnum.USER.name().equals(account.getRole())) {
            // 调用UserService的login方法进行普通用户登录验证，并获取登录后的Account对象
            account = userService.login(account);
        } else {
            return Result.error(ResultCodeEnum.PARAM_ERROR);
        }
        // 登录验证通过（无论普通用户还是管理员），返回成功响应，包含登录后的Account信息
        return Result.success(account);
    }

    /**
     * 用户注册接口
     *
     * @param account 注册账号信息对象（Account实体类）
     * @return Result 对象，表示注册操作成功；或错误信息（如参数缺失）
     */
    @Operation(summary = "用户注册接口", description = "用户注册接口")
    @Parameter(name = "account", description = "注册账号信息对象（Account实体类）", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "成功"),
                @ApiResponse(responseCode = "4001", description = "参数缺失"),
                @ApiResponse(responseCode = "400", description = "参数异常"),
                @ApiResponse(responseCode = "5001", description = "用户名已存在")
            })
    @PostMapping("/register")
    public Result register(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername())
                || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getRole())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.USER.name().equals(account.getRole())) {
            userService.register(account);
        } else {
            return Result.error(ResultCodeEnum.PARAM_ERROR);
        }
        return Result.success();
    }

    /**
     * 修改用户密码接口
     *
     * @param account 包含用户名、原密码、新密码的账号信息对象（Account实体类）
     * @return Result 对象，表示修改密码操作成功；或错误信息（如参数缺失）
     */
    @Operation(summary = "修改用户密码接口", description = "修改用户密码接口")
    @Parameter(name = "account", description = "包含用户名、原密码、新密码的账号信息对象（Account实体类）", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(responseCode = "200", description = "成功"),
                @ApiResponse(responseCode = "4001", description = "参数缺失"),
                @ApiResponse(responseCode = "400", description = "参数异常"),
                @ApiResponse(responseCode = "5005", description = "原密码输入错误")
            })
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Account account) {
        if (StrUtil.isBlank(account.getUsername())
                || StrUtil.isBlank(account.getPassword())
                || ObjectUtil.isEmpty(account.getNewPassword())) {
            return Result.error(ResultCodeEnum.PARAM_LOST_ERROR);
        }
        if (RoleEnum.ADMIN.name().equals(account.getRole())) {
            adminService.updatePassword(account);
        } else if (RoleEnum.USER.name().equals(account.getRole())) {
            userService.updatePassword(account);
        } else {
            return Result.error(ResultCodeEnum.PARAM_ERROR);
        }
        return Result.success();
    }
}
