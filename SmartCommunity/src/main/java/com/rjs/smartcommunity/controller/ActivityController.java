package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.Activity;
import com.rjs.smartcommunity.service.ActivityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

/**
 * 活动表前端操作接口控制器
 *
 * @author rjs
 */
@Tag(name = "ActivityController", description = "活动表前端操作接口控制器")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    /** 注入活动服务 */
    @Resource private ActivityService activityService;

    /**
     * 新增活动接口
     *
     * @param activity 待新增的活动对象（Activity实体类）
     * @return Result 对象，表示操作成功
     */
    @Operation(summary = "新增活动接口", description = "新增活动接口")
    @Parameter(name = "activity", description = "待新增的活动对象（Activity实体类）", required = true)
    @PostMapping("/add")
    public Result add(@RequestBody Activity activity) {
        activityService.add(activity);
        return Result.success();
    }

    /**
     * 删除活动接口（根据ID）
     *
     * @param id 活动ID（Integer类型）
     * @return Result 对象，表示操作成功
     */
    @Operation(summary = "删除活动接口（根据ID）", description = "删除活动接口（根据ID）")
    @Parameter(name = "id", description = "活动ID（Integer类型）", required = true)
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        activityService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除活动接口
     *
     * @param ids 待删除的活动ID列表（List<Integer>类型）
     * @return Result 对象，表示操作成功
     */
    @Operation(summary = "批量删除活动接口", description = "批量删除活动接口")
    @Parameter(name = "ids", description = "待删除的活动ID列表（List<Integer>类型）", required = true)
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        activityService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 更新活动接口（根据ID）
     *
     * @param activity 待更新的活动对象（Activity实体类）
     * @return Result 对象，表示操作成功
     */
    @Operation(summary = "更新活动接口（根据ID）", description = "更新活动接口（根据ID）")
    @Parameter(name = "activity", description = "待更新的活动对象（Activity实体类）", required = true)
    @PutMapping("/update")
    public Result updateById(@RequestBody Activity activity) {
        activityService.updateById(activity);
        return Result.success();
    }

    /**
     * 根据ID查询活动接口
     *
     * @param id 活动ID（Integer类型）
     * @return Result 对象，包含查询到的活动（Activity实体类）
     */
    @Operation(summary = "根据ID查询活动接口", description = "根据ID查询活动接口")
    @Parameter(name = "id", description = "活动ID（Integer类型）", required = true)
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Activity activity = activityService.selectById(id);
        return Result.success(activity);
    }

    /**
     * 查询所有活动接口
     *
     * @param activity 可选条件参数（Activity实体类，可为空）
     * @return Result 对象，包含所有活动列表（List<Activity>类型）
     */
    @Operation(summary = "查询所有活动接口", description = "查询所有活动接口")
    @Parameter(name = "activity", description = "可选条件参数（Activity实体类，可为空）")
    @GetMapping("/selectAll")
    public Result selectAll(Activity activity) {
        List<Activity> list = activityService.selectAll(activity);
        return Result.success(list);
    }

    /**
     * 分页查询活动接口
     *
     * @param activity 可选条件参数（Activity实体类，可为空）
     * @param pageNum 当前页码（Integer类型，默认值1）
     * @param pageSize 每页大小（Integer类型，默认值10）
     * @return Result 对象，包含分页查询结果（PageInfo<Activity>类型）
     */
    @Operation(summary = "分页查询活动接口", description = "分页查询活动接口")
    @Parameters({
        @Parameter(name = "activity", description = "可选条件参数（Activity实体类，可为空）"),
        @Parameter(name = "pageNum", description = "当前页码（Integer类型，默认值1）"),
        @Parameter(name = "pageSize", description = "每页大小（Integer类型，默认值10）")
    })
    @GetMapping("/selectPage")
    public Result selectPage(
            Activity activity,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Activity> page = activityService.selectPage(activity, pageNum, pageSize);
        return Result.success(page);
    }
}
