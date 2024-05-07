package com.rjs.smartcommunity.controller;

import cn.hutool.core.lang.Dict;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.Reserve;
import com.rjs.smartcommunity.service.ReserveService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 服务预约前端操作接口
 *
 * @author rjs
 */
@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Resource private ReserveService reserveService;

    /**
     * 新增服务预约
     *
     * @param reserve 预约信息对象
     * @return 返回操作结果
     */
    @PostMapping("/add")
    public Result add(@RequestBody Reserve reserve) {
        reserveService.add(reserve);
        return Result.success();
    }

    /**
     * 根据ID删除服务预约
     *
     * @param id 预约ID
     * @return 返回操作结果
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        reserveService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除服务预约
     *
     * @param ids 预约ID列表
     * @return 返回操作结果
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        reserveService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改服务预约信息
     *
     * @param reserve 预约信息对象
     * @return 返回操作结果
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Reserve reserve) {
        reserveService.updateById(reserve);
        return Result.success();
    }

    /**
     * 根据ID查询服务预约信息
     *
     * @param id 预约ID
     * @return 返回查询结果，包含预约信息
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Reserve reserve = reserveService.selectById(id);
        return Result.success(reserve);
    }

    /**
     * 查询所有服务预约信息
     *
     * @param reserve 预约信息筛选条件
     * @return 返回查询结果列表
     */
    @GetMapping("/selectAll")
    public Result selectAll(Reserve reserve) {
        List<Reserve> list = reserveService.selectAll(reserve);
        return Result.success(list);
    }

    /**
     * 分页查询服务预约信息
     *
     * @param reserve 预约信息筛选条件
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return 返回分页查询结果
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Reserve reserve,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Reserve> page = reserveService.selectPage(reserve, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 查询并返回字典表中的记录总数。 该方法不接受任何参数，通过调用{@code reserveService.selectCount()}方法查询字典表中的记录总数，
     * 然后将查询结果封装在{@code Result}对象中返回。
     *
     * @return {@code Result}对象，其中包含字典表的记录总数。
     */
    @GetMapping("/selectCount")
    public Result selectCount() {
        // 通过服务层方法查询字典表记录总数
        List<Dict> dictList = reserveService.selectCount();
        // 将查询结果封装成成功结果并返回
        return Result.success(dictList);
    }
}
