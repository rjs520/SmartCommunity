package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.User;
import com.rjs.smartcommunity.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

/**
 * 用户前端操作接口控制器，提供RESTful API以供前端进行用户的增删改查及分页查询操作。
 *
 * @author rjs
 */
@Tag(name = "UserController", description = "用户前端操作接口控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    /** 注入用户服务对象，用于处理与用户相关的业务逻辑。 */
    @Resource private UserService userService;

    /**
     * 新增用户接口。 请求方式：POST 请求地址：/user/add 请求体：JSON格式的{@link User}对象
     *
     * @param user 待新增的用户对象，包含所需的所有属性信息
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @Operation(summary = "新增用户接口", description = "新增用户接口")
    @Parameter(name = "user", description = "待新增的用户对象，包含所需的所有属性信息", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "5001", description = "用户名已存在", content = @Content),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "4001", description = "参数缺失", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content)
            })
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    /**
     * 根据ID删除用户接口。 请求方式：DELETE 请求地址：/user/delete/{id} URL参数：{id} - 要删除的用户ID
     *
     * @param id 要删除的用户ID
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @Operation(summary = "根据ID删除用户接口", description = "根据ID删除用户接口")
    @Parameter(name = "id", description = "要删除的用户ID", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content)
            })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除用户接口。 请求方式：DELETE 请求地址：/user/delete/batch 请求体：JSON格式的用户ID列表
     *
     * @param ids 要批量删除的用户ID列表
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @Operation(summary = "批量删除用户接口", description = "批量删除用户接口")
    @Parameter(name = "ids", description = "要批量删除的用户ID列表", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content)
            })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新用户接口。 请求方式：PUT 请求地址：/user/update 请求体：JSON格式的更新后的{@link User}对象
     *
     * @param user 更新后的用户对象，包含需要修改的属性信息
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @Operation(summary = "更新用户接口", description = "更新用户接口")
    @Parameter(name = "user", description = "更新后的用户对象，包含需要修改的属性信息", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content)
            })
    @PutMapping("/update")
    public Result updateById(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    /**
     * 根据ID查询用户接口。 请求方式：GET 请求地址：/user/selectById/{id} URL参数：{id} - 要查询的用户ID
     *
     * @param id 要查询的用户ID
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"，数据部分包含查询到的用户对象
     */
    @Operation(summary = "根据ID查询用户接口", description = "根据ID查询用户接口")
    @Parameter(name = "id", description = "要查询的用户ID", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content)
            })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }

    /**
     * 查询所有用户接口。 请求方式：GET 请求地址：/user/selectAll 请求参数（可选）：传入一个{@link User}对象作为查询条件，可包含部分属性信息
     *
     * @param user 可选的查询条件对象，包含部分或全部属性信息
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"，数据部分包含查询到的用户列表
     */
    @Operation(summary = "查询所有用户接口", description = "查询所有用户接口")
    @Parameter(name = "user", description = "可选的查询条件对象，包含部分或全部属性信息")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content)
            })
    @GetMapping("/selectAll")
    public Result selectAll(User user) {
        List<User> list = userService.selectAll(user);
        return Result.success(list);
    }

    /**
     * 分页查询用户接口。 请求方式：GET 请求地址：/user/selectPage 请求参数（可选）： - 查询条件对象（{@link User}），包含部分或全部属性信息 -
     * pageNum：当前页码，默认为1 - pageSize：每页记录数，默认为10
     *
     * @param user 可选的查询条件对象，包含部分或全部属性信息
     * @param pageNum 当前页码
     * @param pageSize 每页记录数
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"，数据部分包含分页查询结果的封装对象（{@link PageInfo}）
     */
    @Operation(summary = "分页查询用户接口", description = "分页查询用户接口")
    @Parameters({
        @Parameter(name = "user", description = "可选的查询条件对象，包含部分或全部属性信息"),
        @Parameter(name = "pageNum", description = "当前页码，默认为1"),
        @Parameter(name = "pageSize", description = "每页记录数，默认为10")
    })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content)
            })
    @GetMapping("/selectPage")
    public Result selectPage(
            User user,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPage(user, pageNum, pageSize);
        return Result.success(page);
    }
}
