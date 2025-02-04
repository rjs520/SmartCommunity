package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.Serve;
import com.rjs.smartcommunity.service.ServeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 社区服务表前端操作接口
 *
 * @author rjs
 */
@Tag(name = "ServeController", description = "社区服务前端操作接口")
@RestController
@RequestMapping("/serve")
public class ServeController {
    @Resource private ServeService serveService;

    /**
     * 新增社区服务信息
     *
     * @param serve 社区服务实体对象
     * @return 操作结果，成功返回success
     */
    @Operation(summary = "新增社区服务信息", description = "新增社区服务信息")
    @Parameter(name = "serve", description = "社区服务实体对象", required = true)
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
    @PostMapping("/add")
    public Result add(@RequestBody Serve serve) {
        serveService.add(serve);
        return Result.success();
    }

    /**
     * 根据ID删除社区服务信息
     *
     * @param id 社区服务ID
     * @return 操作结果，成功返回success
     */
    @Operation(summary = "根据ID删除社区服务信息", description = "根据ID删除社区服务信息")
    @Parameter(name = "id", description = "社区服务ID", required = true)
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
        serveService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除社区服务信息
     *
     * @param ids 社区服务ID列表
     * @return 操作结果，成功返回success
     */
    @Operation(summary = "批量删除社区服务信息", description = "批量删除社区服务信息")
    @Parameter(name = "ids", description = "社区服务ID列表", required = true)
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
        serveService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改社区服务信息
     *
     * @param serve 社区服务实体对象
     * @return 操作结果，成功返回success
     */
    @Operation(summary = "根据ID修改社区服务信息", description = "根据ID修改社区服务信息")
    @Parameter(name = "serve", description = "社区服务实体对象", required = true)
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
    public Result updateById(@RequestBody Serve serve) {
        serveService.updateById(serve);
        return Result.success();
    }

    /**
     * 根据ID查询社区服务信息
     *
     * @param id 社区服务ID
     * @return 操作结果，包含查询到的社区服务信息
     */
    @Operation(summary = "根据ID查询社区服务信息", description = "根据ID查询社区服务信息")
    @Parameter(name = "id", description = "社区服务ID", required = true)
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
        Serve serve = serveService.selectById(id);
        return Result.success(serve);
    }

    /**
     * 查询所有社区服务信息
     *
     * @param serve 社区服务实体对象（可选条件）
     * @return 操作结果，包含所有社区服务信息列表
     */
    @Operation(summary = "查询所有社区服务信息", description = "查询所有社区服务信息")
    @Parameter(name = "serve", description = "社区服务实体对象（可选条件）")
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
    public Result selectAll(Serve serve) {
        List<Serve> list = serveService.selectAll(serve);
        return Result.success(list);
    }

    /**
     * 分页查询社区服务信息
     *
     * @param serve 社区服务实体对象（可选条件）
     * @param pageNum 页码
     * @param pageSize 每页显示数量
     * @return 操作结果，包含分页查询到的社区服务信息
     */
    @Operation(summary = "分页查询社区服务信息", description = "分页查询社区服务信息")
    @Parameters({
        @Parameter(name = "serve", description = "社区服务实体对象（可选条件）"),
        @Parameter(name = "pageNum", description = "页码"),
        @Parameter(name = "pageSize", description = "每页显示数量")
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
            Serve serve,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Serve> page = serveService.selectPage(serve, pageNum, pageSize);
        return Result.success(page);
    }
}
