package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.ParkingSign;
import com.rjs.smartcommunity.service.ParkingSignService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 车位预约前端操作接口
 *
 * @author rjs
 */
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
    @GetMapping("/selectPage")
    public Result selectPage(
            ParkingSign parkingSign,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<ParkingSign> page = parkingSignService.selectPage(parkingSign, pageNum, pageSize);
        return Result.success(page);
    }
}
