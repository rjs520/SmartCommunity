package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.Building;
import com.rjs.smartcommunity.service.BuildingService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 楼宇前端操作接口
 *
 * @author rjs
 */
@RestController
@RequestMapping("/building")
public class BuildingController {
    @Resource private BuildingService buildingService;

    /**
     * 新增楼宇信息
     *
     * @param building 包含楼宇信息的对象
     * @return 返回操作结果，成功则返回success结果
     */
    @PostMapping("/add")
    public Result add(@RequestBody Building building) {
        buildingService.add(building);
        return Result.success();
    }

    /**
     * 根据ID删除楼宇信息
     *
     * @param id 楼宇的ID
     * @return 返回操作结果，成功则返回success结果
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        buildingService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除楼宇信息
     *
     * @param ids 包含需要删除的楼宇ID的列表
     * @return 返回操作结果，成功则返回success结果
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        buildingService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改楼宇信息
     *
     * @param building 包含修改后的楼宇信息的对象
     * @return 返回操作结果，成功则返回success结果
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Building building) {
        buildingService.updateById(building);
        return Result.success();
    }

    /**
     * 根据ID查询楼宇信息
     *
     * @param id 楼宇的ID
     * @return 返回查询结果，成功则返回包含查询到的楼宇信息的对象
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Building building = buildingService.selectById(id);
        return Result.success(building);
    }

    /**
     * 查询所有楼宇信息
     *
     * @param building 包含过滤条件的楼宇对象，可选
     * @return 返回操作结果，成功则返回包含所有楼宇信息的列表
     */
    @GetMapping("/selectAll")
    public Result selectAll(Building building) {
        List<Building> list = buildingService.selectAll(building);
        return Result.success(list);
    }

    /**
     * 分页查询楼宇信息
     *
     * @param building 包含过滤条件的楼宇对象，可选
     * @param pageNum 请求的页码，默认为1
     * @param pageSize 每页显示的数量，默认为10
     * @return 返回操作结果，成功则返回包含分页信息的PageInfo对象
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Building building,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Building> page = buildingService.selectPage(building, pageNum, pageSize);
        return Result.success(page);
    }
}
