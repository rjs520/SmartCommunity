package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.NurseHouse;
import com.rjs.smartcommunity.service.NurseHouseService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 养老院前端操作接口
 *
 * @author rjs
 */
@RestController
@RequestMapping("/nurseHouse")
public class NurseHouseController {
    @Resource private NurseHouseService nurseHouseService;

    /**
     * 新增养老院信息
     *
     * @param nurseHouse 养老院实体对象，包含养老院详细信息
     * @return 返回操作结果，成功返回success
     */
    @PostMapping("/add")
    public Result add(@RequestBody NurseHouse nurseHouse) {
        nurseHouseService.add(nurseHouse);
        return Result.success();
    }

    /**
     * 根据ID删除养老院信息
     *
     * @param id 养老院ID，用于指定删除的养老院
     * @return 返回操作结果，成功返回success
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        nurseHouseService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除养老院信息
     *
     * @param ids 养老院ID列表，用于指定删除的养老院集合
     * @return 返回操作结果，成功返回success
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        nurseHouseService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改养老院信息
     *
     * @param nurseHouse 养老院实体对象，包含需要更新的养老院详细信息
     * @return 返回操作结果，成功返回success
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody NurseHouse nurseHouse) {
        nurseHouseService.updateById(nurseHouse);
        return Result.success();
    }

    /**
     * 更新计数。
     *
     * @param id 养老院的唯一标识符，通过路径变量传递。
     * @return 返回操作结果，如果操作成功，则返回一个成功的Result对象。
     */
    @PutMapping("/updateCount/{id}")
    public Result updateCount(@PathVariable Integer id) {
        // 调用服务层方法，更新指定ID的养老院计数
        nurseHouseService.updateCount(id);
        // 返回操作成功的结果
        return Result.success();
    }

    /**
     * 根据ID查询养老院信息
     *
     * @param id 养老院ID，用于指定查询的养老院
     * @return 返回操作结果，包含查询到的养老院信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        NurseHouse nurseHouse = nurseHouseService.selectById(id);
        return Result.success(nurseHouse);
    }

    /**
     * 查询所有养老院信息
     *
     * @param nurseHouse 养老院实体对象，可选条件用于查询
     * @return 返回操作结果，包含所有查询到的养老院信息列表
     */
    @GetMapping("/selectAll")
    public Result selectAll(NurseHouse nurseHouse) {
        List<NurseHouse> list = nurseHouseService.selectAll(nurseHouse);
        return Result.success(list);
    }

    /**
     * 分页查询养老院信息
     *
     * @param nurseHouse 养老院实体对象，可选条件用于查询
     * @param pageNum 查询页码
     * @param pageSize 每页显示数量
     * @return 返回操作结果，包含分页查询到的养老院信息
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            NurseHouse nurseHouse,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<NurseHouse> page = nurseHouseService.selectPage(nurseHouse, pageNum, pageSize);
        return Result.success(page);
    }
}
