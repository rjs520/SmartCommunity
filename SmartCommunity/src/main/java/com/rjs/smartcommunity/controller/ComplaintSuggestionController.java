package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.ComplaintSuggestion;
import com.rjs.smartcommunity.service.ComplaintSuggestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 投诉与建议前端操作接口
 *
 * @author rjs
 */
@Tag(name = "complaintSuggestion", description = "投诉与建议前端操作接口")
@RestController
@RequestMapping("/complaintSuggestion")
public class ComplaintSuggestionController {
    @Resource private ComplaintSuggestionService complaintSuggestionService;

    /**
     * 新增投诉与建议
     *
     * @param complaintSuggestion 投诉与建议实体
     * @return 操作结果
     */
    @Operation(summary = "新增投诉与建议", description = "新增投诉与建议")
    @Parameter(name = "complaintSuggestion", description = "投诉与建议实体", required = true)
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
    public Result add(@RequestBody ComplaintSuggestion complaintSuggestion) {
        complaintSuggestionService.add(complaintSuggestion);
        return Result.success();
    }

    /**
     * 根据ID删除投诉与建议
     *
     * @param id 投诉与建议ID
     * @return 操作结果
     */
    @Operation(summary = "根据ID删除投诉与建议", description = "根据ID删除投诉与建议")
    @Parameter(name = "id", description = "投诉与建议ID", required = true)
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
        complaintSuggestionService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除投诉与建议
     *
     * @param ids 投诉与建议ID列表
     * @return 操作结果
     */
    @Operation(summary = "批量删除投诉与建议", description = "批量删除投诉与建议")
    @Parameter(name = "ids", description = "投诉与建议ID列表", required = true)
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
        complaintSuggestionService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改投诉与建议
     *
     * @param complaintSuggestion 投诉与建议实体
     * @return 操作结果
     */
    @Operation(summary = "根据ID修改投诉与建议", description = "根据ID修改投诉与建议")
    @Parameter(name = "complaintSuggestion", description = "投诉与建议实体", required = true)
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
    public Result updateById(@RequestBody ComplaintSuggestion complaintSuggestion) {
        complaintSuggestionService.updateById(complaintSuggestion);
        return Result.success();
    }

    /**
     * 根据ID查询投诉与建议
     *
     * @param id 投诉与建议ID
     * @return 查询结果
     */
    @Operation(summary = "根据ID查询投诉与建议", description = "根据ID查询投诉与建议")
    @Parameter(name = "id", description = "投诉与建议ID", required = true)
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
        ComplaintSuggestion complaintSuggestion = complaintSuggestionService.selectById(id);
        return Result.success(complaintSuggestion);
    }

    /**
     * 查询所有投诉与建议
     *
     * @param complaintSuggestion 投诉与建议实体（可选条件）
     * @return 查询结果列表
     */
    @Operation(summary = "查询所有投诉与建议", description = "查询所有投诉与建议")
    @Parameter(name = "complaintSuggestion", description = "投诉与建议实体（可选条件）")
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
    public Result selectAll(ComplaintSuggestion complaintSuggestion) {
        List<ComplaintSuggestion> list = complaintSuggestionService.selectAll(complaintSuggestion);
        return Result.success(list);
    }

    /**
     * 分页查询投诉与建议
     *
     * @param complaintSuggestion 投诉与建议实体（可选条件）
     * @param pageNum 页码
     * @param pageSize 页面大小
     * @return 分页查询结果
     */
    @Operation(summary = "分页查询投诉与建议", description = "分页查询投诉与建议")
    @Parameters({
        @Parameter(name = "complaintSuggestion", description = "投诉与建议实体（可选条件）"),
        @Parameter(name = "pageNum", description = "页码"),
        @Parameter(name = "pageSize", description = "页面大小")
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
            ComplaintSuggestion complaintSuggestion,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<ComplaintSuggestion> page =
                complaintSuggestionService.selectPage(complaintSuggestion, pageNum, pageSize);
        return Result.success(page);
    }
}
