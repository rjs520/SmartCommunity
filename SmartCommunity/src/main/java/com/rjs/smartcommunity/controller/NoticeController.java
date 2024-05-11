package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.Notice;
import com.rjs.smartcommunity.service.NoticeService;

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
 * 公告信息表前端操作接口控制器
 *
 * @author rjs
 */
@Tag(name = "NoticeController", description = "公告信息表前端操作接口控制器")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    /** 注入公告信息服务 */
    @Resource private NoticeService noticeService;

    /**
     * 新增公告信息接口
     *
     * @param notice 待新增的公告信息对象（Notice实体类）
     * @return Result 对象，表示操作成功
     */
    @Operation(summary = "新增公告信息接口", description = "新增公告信息接口")
    @Parameter(name = "notice", description = "待新增的公告信息对象（Notice实体类）", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "操作成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
            })
    @PostMapping("/add")
    public Result add(@RequestBody Notice notice) {
        noticeService.add(notice);
        return Result.success();
    }

    /**
     * 删除公告信息接口（根据ID）
     *
     * @param id 公告信息ID（Integer类型）
     * @return Result 对象，表示操作成功
     */
    @Operation(summary = "删除公告信息接口（根据ID）", description = "删除公告信息接口（根据ID）")
    @Parameter(name = "id", description = "公告信息ID（Integer类型）", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "操作成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
            })
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        noticeService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除公告信息接口
     *
     * @param ids 待删除的公告信息ID列表（List<Integer>类型）
     * @return Result 对象，表示操作成功
     */
    @Operation(summary = "批量删除公告信息接口", description = "批量删除公告信息接口")
    @Parameter(name = "ids", description = "待删除的公告信息ID列表（List<Integer>类型）", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "操作成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
            })
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        noticeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新公告信息接口（根据ID）
     *
     * @param notice 待更新的公告信息对象（Notice实体类）
     * @return Result 对象，表示操作成功
     */
    @Operation(summary = "更新公告信息接口（根据ID）", description = "更新公告信息接口（根据ID）")
    @Parameter(name = "notice", description = "待更新的公告信息对象（Notice实体类）", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "操作成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
            })
    @PutMapping("/update")
    public Result updateById(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return Result.success();
    }

    /**
     * 根据ID查询公告信息接口
     *
     * @param id 公告信息ID（Integer类型）
     * @return Result 对象，包含查询到的公告信息（Notice实体类）
     */
    @Operation(summary = "根据ID查询公告信息接口", description = "根据ID查询公告信息接口")
    @Parameter(name = "id", description = "公告信息ID（Integer类型）", required = true)
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "操作成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
            })
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Notice notice = noticeService.selectById(id);
        return Result.success(notice);
    }

    /**
     * 查询所有公告信息接口
     *
     * @param notice 可选条件参数（Notice实体类，可为空）
     * @return Result 对象，包含所有公告信息列表（List<Notice>类型）
     */
    @Operation(summary = "查询所有公告信息接口", description = "查询所有公告信息接口")
    @Parameter(name = "notice", description = "可选条件参数（Notice实体类，可为空）")
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "操作成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
            })
    @GetMapping("/selectAll")
    public Result selectAll(Notice notice) {
        List<Notice> list = noticeService.selectAll(notice);
        return Result.success(list);
    }

    /**
     * 分页查询公告信息接口
     *
     * @param notice 可选条件参数（Notice实体类，可为空）
     * @param pageNum 当前页码（Integer类型，默认值1）
     * @param pageSize 每页大小（Integer类型，默认值10）
     * @return Result 对象，包含分页查询结果（PageInfo<Notice>类型）
     */
    @Operation(summary = "分页查询公告信息接口", description = "分页查询公告信息接口")
    @Parameters({
        @Parameter(name = "notice", description = "可选条件参数（Notice实体类，可为空）"),
        @Parameter(name = "pageNum", description = "当前页码（Integer类型，默认值1）"),
        @Parameter(name = "pageSize", description = "每页大小（Integer类型，默认值10）")
    })
    @ApiResponses(
            value = {
                @ApiResponse(
                        responseCode = "200",
                        description = "操作成功",
                        content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Result.class))),
                @ApiResponse(responseCode = "500", description = "系统异常", content = @Content),
                @ApiResponse(responseCode = "400", description = "参数异常", content = @Content),
            })
    @GetMapping("/selectPage")
    public Result selectPage(
            Notice notice,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Notice> page = noticeService.selectPage(notice, pageNum, pageSize);
        return Result.success(page);
    }
}
