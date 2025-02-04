package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.Admin;
import com.rjs.smartcommunity.service.AdminService;

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
 * 管理员前端操作接口控制器，提供RESTful API以供前端进行管理员的增删改查及分页查询操作。
 *
 * @author rjs
 */
@Tag(name = "Admin", description = "管理员相关操作")
@RestController
@RequestMapping("/admin")
public class AdminController {

    /** 注入管理员服务对象，用于处理与管理员相关的业务逻辑。 */
    @Resource private AdminService adminService;

    /**
     * 新增管理员接口。 请求方式：POST 请求地址：/admin/add 请求体：JSON格式的{@link Admin}对象
     *
     * @param admin 待新增的管理员对象，包含所需的所有属性信息
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @Operation(summary = "新增管理员", description = "新增管理员")
    @Parameter(name = "admin", description = "管理员对象", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class))
                        }),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "401", description = "token验证失败，请重新登录", content = @Content),
            })
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success();
    }

    /**
     * 根据ID删除管理员接口。 请求方式：DELETE 请求地址：/admin/delete/{id} URL参数：{id} - 要删除的管理员ID
     *
     * @param id 要删除的管理员ID
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @Operation(summary = "删除管理员", description = "根据ID删除管理员")
    @Parameter(name = "id", description = "管理员ID", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class))
                        }),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "401", description = "token验证失败，请重新登录", content = @Content),
            })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除管理员接口。 请求方式：DELETE 请求地址：/admin/delete/batch 请求体：JSON格式的管理员ID列表
     *
     * @param ids 要批量删除的管理员ID列表
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @Operation(summary = "批量删除管理员", description = "批量删除管理员")
    @Parameter(name = "ids", description = "管理员ID列表", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class))
                        }),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "401", description = "token验证失败，请重新登录", content = @Content),
            })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        adminService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新管理员接口。 请求方式：PUT 请求地址：/admin/update 请求体：JSON格式的更新后的{@link Admin}对象
     *
     * @param admin 更新后的管理员对象，包含需要修改的属性信息
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"表示成功
     */
    @Operation(summary = "更新管理员", description = "更新管理员")
    @Parameter(name = "admin", description = "管理员对象", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class))
                        }),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "401", description = "token验证失败，请重新登录", content = @Content),
            })
    @PutMapping("/update")
    public Result updateById(@RequestBody Admin admin) {
        adminService.updateById(admin);
        return Result.success();
    }

    /**
     * 根据ID查询管理员接口。 请求方式：GET 请求地址：/admin/selectById/{id} URL参数：{id} - 要查询的管理员ID
     *
     * @param id 要查询的管理员ID
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"，数据部分包含查询到的管理员对象
     */
    @Operation(summary = "根据ID查询管理员", description = "根据ID查询管理员")
    @Parameter(name = "id", description = "管理员ID", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class))
                        }),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "401", description = "token验证失败，请重新登录", content = @Content),
            })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Admin admin = adminService.selectById(id);
        return Result.success(admin);
    }

    /**
     * 查询所有管理员接口。 请求方式：GET 请求地址：/admin/selectAll 请求参数（可选）：传入一个{@link Admin}对象作为查询条件，可包含部分属性信息
     *
     * @param admin 可选的查询条件对象，包含部分或全部属性信息
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"，数据部分包含查询到的管理员列表
     */
    @Operation(summary = "查询所有管理员", description = "查询所有管理员")
    @Parameter(name = "admin", description = "管理员对象")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class))
                        }),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "401", description = "token验证失败，请重新登录", content = @Content),
            })
    @GetMapping("/selectAll")
    public Result selectAll(Admin admin) {
        List<Admin> list = adminService.selectAll(admin);
        return Result.success(list);
    }

    /**
     * 分页查询管理员接口。 请求方式：GET 请求地址：/admin/selectPage 请求参数（可选）： - 查询条件对象（{@link Admin}），包含部分或全部属性信息 -
     * pageNum：当前页码，默认为1 - pageSize：每页记录数，默认为10
     *
     * @param admin 可选的查询条件对象，包含部分或全部属性信息
     * @param pageNum 当前页码
     * @param pageSize 每页记录数
     * @return 返回表示操作结果的{@link Result}对象，状态码为"200"，数据部分包含分页查询结果的封装对象（{@link PageInfo}）
     */
    @Operation(summary = "分页查询管理员", description = "分页查询管理员")
    @Parameters({
        @Parameter(name = "admin", description = "管理员对象"),
        @Parameter(name = "pageNum", description = "当前页码"),
        @Parameter(name = "pageSize", description = "每页记录数")
    })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "成功",
                        content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Result.class))
                        }),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "401", description = "token验证失败，请重新登录", content = @Content),
            })
    @GetMapping("/selectPage")
    public Result selectPage(
            Admin admin,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Admin> page = adminService.selectPage(admin, pageNum, pageSize);
        return Result.success(page);
    }
}
