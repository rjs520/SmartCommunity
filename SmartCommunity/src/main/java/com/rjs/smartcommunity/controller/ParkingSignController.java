package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.ParkingSign;
import com.rjs.smartcommunity.service.ParkingSignService;
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
 * 车位预约前端操作接口
 *
 * @author rjs
 */
@Tag(name = "ParkingSignController", description = "车位预约前端操作接口")
@RestController
@RequestMapping("/parkingSign")
public class ParkingSignController {
    @Resource private ParkingSignService parkingSignService;

    /**
     * 新增车位预约信息
     *
     * @param parkingSign 车位预约实体
     * @return 操作结果
     */
    @Operation(summary = "新增车位预约信息", description = "新增车位预约信息")
    @Parameter(name = "parkingSign", description = "车位预约实体", required = true)
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
    public Result add(@RequestBody ParkingSign parkingSign) {
        parkingSignService.add(parkingSign);
        return Result.success();
    }

    /**
     * 根据ID删除车位预约信息
     *
     * @param id 预约ID
     * @return 操作结果
     */
    @Operation(summary = "根据ID删除车位预约信息", description = "根据ID删除车位预约信息")
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
        parkingSignService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除车位预约信息
     *
     * @param ids 预约ID集合
     * @return 操作结果
     */
    @Operation(summary = "批量删除车位预约信息", description = "批量删除车位预约信息")
    @Parameter(name = "ids", description = "预约ID集合", required = true)
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
        parkingSignService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改车位预约信息
     *
     * @param parkingSign 车位预约实体
     * @return 操作结果
     */
    @Operation(summary = "根据ID修改车位预约信息", description = "根据ID修改车位预约信息")
    @Parameter(name = "parkingSign", description = "车位预约实体", required = true)
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
    public Result updateById(@RequestBody ParkingSign parkingSign) {
        parkingSignService.updateById(parkingSign);
        return Result.success();
    }

    /**
     * 根据ID查询车位预约信息
     *
     * @param id 预约ID
     * @return 车位预约信息
     */
    @Operation(summary = "根据ID查询车位预约信息", description = "根据ID查询车位预约信息")
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
        ParkingSign parkingSign = parkingSignService.selectById(id);
        return Result.success(parkingSign);
    }

    /**
     * 查询所有车位预约信息
     *
     * @param parkingSign 车位预约实体（可选条件）
     * @return 车位预约信息列表
     */
    @Operation(summary = "查询所有车位预约信息", description = "查询所有车位预约信息")
    @Parameter(name = "parkingSign", description = "车位预约实体（可选条件）")
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
    public Result selectAll(ParkingSign parkingSign) {
        List<ParkingSign> list = parkingSignService.selectAll(parkingSign);
        return Result.success(list);
    }

    /**
     * 分页查询车位预约信息
     *
     * @param parkingSign 车位预约实体（可选条件）
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @return 分页查询结果
     */
    @Operation(summary = "分页查询车位预约信息", description = "分页查询车位预约信息")
    @Parameters({
        @Parameter(name = "parkingSign", description = "车位预约实体（可选条件）"),
        @Parameter(name = "pageNum", description = "页码", required = true),
        @Parameter(name = "pageSize", description = "每页数量", required = true)
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
            ParkingSign parkingSign,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<ParkingSign> page = parkingSignService.selectPage(parkingSign, pageNum, pageSize);
        return Result.success(page);
    }
}
