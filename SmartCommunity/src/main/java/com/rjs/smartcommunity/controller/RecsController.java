package com.rjs.smartcommunity.controller;

import cn.hutool.core.lang.Dict;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;

import com.rjs.smartcommunity.entity.Recs;
import com.rjs.smartcommunity.service.RecsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

/**
 * 反馈与建议前端操作接口
 *
 * @author rjs
 */
@Tag(name = "RecsController", description = "反馈与建议前端操作接口")
@RestController
@RequestMapping("/recs")
public class RecsController {

    @Resource private RecsService recsService;

    /**
     * 新增反馈与建议的接口。 通过接收一个预约信息对象的列表，将这些预约信息添加到系统中。
     *
     * @param recs 预约信息对象，包含反馈与建议的详细信息。
     * @return 返回一个操作结果对象，表明操作是否成功。
     */
    @Operation(summary = "新增反馈与建议", description = "新增反馈与建议")
    @Parameter(name = "recs", description = "预约信息对象", required = true)
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
    public Result add(@RequestBody Recs recs) {
        // 将接收到的单个预约信息添加到列表中
        List<Recs> recsList = new ArrayList<>();
        recsList.add(recs);
        // 调用服务层方法，将预约信息列表添加到系统
        recsService.add(recsList);
        // 返回操作成功的结果
        return Result.success();
    }

    /**
     * 根据ID删除反馈与建议
     *
     * @param id 预约ID
     * @return 返回操作结果
     */
    @Operation(summary = "根据ID删除反馈与建议", description = "根据ID删除反馈与建议")
    @Parameter(name = "id", description = "预约ID", required = true)
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
        recsService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除反馈与建议
     *
     * @param ids 预约ID列表
     * @return 返回操作结果
     */
    @Operation(summary = "批量删除反馈与建议", description = "批量删除反馈与建议")
    @Parameter(name = "ids", description = "预约ID列表", required = true)
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
        recsService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改反馈与建议信息
     *
     * @param recs 预约信息对象
     * @return 返回操作结果
     */
    @Operation(summary = "根据ID修改反馈与建议信息", description = "根据ID修改反馈与建议信息")
    @Parameter(name = "recs", description = "预约信息对象", required = true)
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
    public Result updateById(@RequestBody Recs recs) {
        recsService.updateById(recs);
        return Result.success();
    }

    /**
     * 根据ID查询反馈与建议信息
     *
     * @param id 预约ID
     * @return 返回查询结果，包含预约信息
     */
    @Operation(summary = "根据ID查询反馈与建议信息", description = "根据ID查询反馈与建议信息")
    @Parameter(name = "id", description = "预约ID", required = true)
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
        Recs recs = recsService.selectById(id);
        return Result.success(recs);
    }

    /**
     * 查询所有反馈与建议信息
     *
     * @param recs 预约信息筛选条件
     * @return 返回查询结果列表
     */
    @Operation(summary = "查询所有反馈与建议信息", description = "查询所有反馈与建议信息")
    @Parameter(name = "recs", description = "预约信息筛选条件")
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
    public Result selectAll(Recs recs) {
        List<Recs> list = recsService.selectAll(recs);
        return Result.success(list);
    }

    /**
     * 分页查询反馈与建议信息
     *
     * @param recs 预约信息筛选条件
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return 返回分页查询结果
     */
    @Operation(summary = "分页查询反馈与建议信息", description = "分页查询反馈与建议信息")
    @Parameters({
        @Parameter(name = "recs", description = "预约信息筛选条件"),
        @Parameter(name = "pageNum", description = "页码"),
        @Parameter(name = "pageSize", description = "每页记录数")
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
            Recs recs,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Recs> page = recsService.selectPage(recs, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 查询并返回字典表中的记录总数。 该方法不接受任何参数，通过调用{@code recsService.selectCount()}方法查询字典表中的记录总数，
     * 然后将查询结果封装在{@code Result}对象中返回。
     *
     * @return {@code Result}对象，其中包含字典表的记录总数。
     */
    @Operation(summary = "查询字典表记录总数", description = "查询字典表记录总数")
    @Parameter(name = "recs", description = "预约信息筛选条件")
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
    @GetMapping("/selectCount")
    public Result selectCount() {
        // 通过服务层方法查询字典表记录总数
        List<Dict> dictList = recsService.selectCount();
        // 将查询结果封装成成功结果并返回
        return Result.success(dictList);
    }
}
