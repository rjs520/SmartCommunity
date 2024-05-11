package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.Parking;
import com.rjs.smartcommunity.service.ParkingService;
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
 * 车位前端操作接口
 *
 * @author rjs
 */
@Tag(name = "ParkingController", description = "车位前端操作接口")
@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Resource private ParkingService parkingService;

    /**
     * 新增车位信息
     *
     * @param parking 包含车位详细信息的对象
     * @return 返回操作结果，成功则返回成功信息
     */
    @Operation(summary = "新增车位信息", description = "新增车位信息")
    @Parameter(name = "parking", description = "车位详细信息的对象", required = true)
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
    public Result add(@RequestBody Parking parking) {
        parkingService.add(parking);
        return Result.success();
    }

    /**
     * 根据ID删除车位信息
     *
     * @param id 车位的唯一标识符
     * @return 返回操作结果，成功则返回成功信息
     */
    @Operation(summary = "根据ID删除车位信息", description = "根据ID删除车位信息")
    @Parameter(name = "id", description = "车位的唯一标识符", required = true)
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
        parkingService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除车位信息
     *
     * @param ids 车位的唯一标识符列表
     * @return 返回操作结果，成功则返回成功信息
     */
    @Operation(summary = "批量删除车位信息", description = "批量删除车位信息")
    @Parameter(name = "ids", description = "车位的唯一标识符列表", required = true)
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
        parkingService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改车位信息
     *
     * @param parking 包含更新后的车位详细信息的对象
     * @return 返回操作结果，成功则返回成功信息
     */
    @Operation(summary = "根据ID修改车位信息", description = "根据ID修改车位信息")
    @Parameter(name = "parking", description = "更新后的车位详细信息的对象", required = true)
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
    public Result updateById(@RequestBody Parking parking) {
        parkingService.updateById(parking);
        return Result.success();
    }

    /**
     * 根据ID查询车位信息
     *
     * @param id 车位的唯一标识符
     * @return 返回查询结果，包含车位的详细信息
     */
    @Operation(summary = "根据ID查询车位信息", description = "根据ID查询车位信息")
    @Parameter(name = "id", description = "车位的唯一标识符", required = true)
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
        Parking parking = parkingService.selectById(id);
        return Result.success(parking);
    }

    /**
     * 查询所有车位信息
     *
     * @param parking 包含查询条件的对象（可选）
     * @return 返回查询结果，包含所有车位的列表
     */
    @Operation(summary = "查询所有车位信息", description = "查询所有车位信息")
    @Parameter(name = "parking", description = "查询条件的对象（可选）")
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
    public Result selectAll(Parking parking) {
        List<Parking> list = parkingService.selectAll(parking);
        return Result.success(list);
    }

    /**
     * 分页查询车位信息
     *
     * @param parking 包含查询条件的对象（可选）
     * @param pageNum 查询页码
     * @param pageSize 每页显示数量
     * @return 返回分页查询结果，包含 PageInfo 对象，内含分页信息和查询结果列表
     */
    @Operation(summary = "分页查询车位信息", description = "分页查询车位信息")
    @Parameters({
        @Parameter(name = "parking", description = "查询条件的对象（可选）"),
        @Parameter(name = "pageNum", description = "查询页码"),
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
            Parking parking,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Parking> page = parkingService.selectPage(parking, pageNum, pageSize);
        return Result.success(page);
    }
}
