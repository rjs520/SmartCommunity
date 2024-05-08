package com.rjs.smartcommunity.controller;

import cn.hutool.core.lang.Dict;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.ActivitySign;
import com.rjs.smartcommunity.service.ActivitySignService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;

/**
 * 活动报名前端操作接口
 *
 * @author rjs
 */
@Tag(name = "ActivitySignController", description = "活动报名前端操作接口")
@RestController
@RequestMapping("/activitySign")
public class ActivitySignController {
    @Resource private ActivitySignService activitySignService;

    /**
     * 新增活动报名信息。
     *
     * @param activitySign 包含活动报名详情的实体对象。
     * @return 返回操作结果，成功则返回success结果。
     */
    @Operation(summary = "新增活动报名信息", description = "新增活动报名信息")
    @Parameter(name = "activitySign", description = "活动报名实体对象", required = true)
    @PostMapping("/add")
    public Result add(@RequestBody ActivitySign activitySign) {
        activitySignService.add(activitySign);
        return Result.success();
    }

    /**
     * 根据ID删除活动报名信息。
     *
     * @param id 活动报名的ID。
     * @return 返回操作结果，成功则返回success结果。
     */
    @Operation(summary = "根据ID删除活动报名信息", description = "根据ID删除活动报名信息")
    @Parameter(name = "id", description = "活动报名的ID", required = true)
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        activitySignService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除活动报名信息。
     *
     * @param ids 活动报名ID的集合。
     * @return 返回操作结果，成功则返回success结果。
     */
    @Operation(summary = "批量删除活动报名信息", description = "批量删除活动报名信息")
    @Parameter(name = "ids", description = "活动报名ID的集合", required = true)
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        activitySignService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改活动报名信息。
     *
     * @param activitySign 包含更新后的活动报名详情的实体对象。
     * @return 返回操作结果，成功则返回success结果。
     */
    @Operation(summary = "根据ID修改活动报名信息", description = "根据ID修改活动报名信息")
    @Parameter(name = "activitySign", description = "活动报名实体对象", required = true)
    @PutMapping("/update")
    public Result updateById(@RequestBody ActivitySign activitySign) {
        activitySignService.updateById(activitySign);
        return Result.success();
    }

    /**
     * 根据ID查询活动报名信息。
     *
     * @param id 活动报名的ID。
     * @return 返回操作结果，成功则返回包含查询结果的Result对象。
     */
    @Operation(summary = "根据ID查询活动报名信息", description = "根据ID查询活动报名信息")
    @Parameter(name = "id", description = "活动报名的ID", required = true)
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        ActivitySign activitySign = activitySignService.selectById(id);
        return Result.success(activitySign);
    }

    /**
     * 查询所有活动报名信息。
     *
     * @param activitySign 活动报名实体对象，可用来过滤查询结果。
     * @return 返回操作结果，成功则返回包含查询结果的List集合的Result对象。
     */
    @Operation(summary = "查询所有活动报名信息", description = "查询所有活动报名信息")
    @Parameter(name = "activitySign", description = "活动报名实体对象", required = true)
    @GetMapping("/selectAll")
    public Result selectAll(ActivitySign activitySign) {
        List<ActivitySign> list = activitySignService.selectAll(activitySign);
        return Result.success(list);
    }

    /**
     * 分页查询活动报名信息。
     *
     * @param activitySign 活动报名实体对象，可用来过滤查询结果。
     * @param pageNum 查询页码。
     * @param pageSize 每页显示的数量。
     * @return 返回操作结果，成功则返回包含分页信息的PageInfo对象的Result对象。
     */
    @Operation(summary = "分页查询活动报名信息", description = "分页查询活动报名信息")
    @Parameters({
        @Parameter(name = "activitySign", description = "活动报名实体对象", required = true),
        @Parameter(name = "pageNum", description = "查询页码", required = true),
        @Parameter(name = "pageSize", description = "每页显示的数量", required = true)
    })
    @GetMapping("/selectPage")
    public Result selectPage(
            ActivitySign activitySign,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<ActivitySign> page =
                activitySignService.selectPage(activitySign, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 查询字典表中记录的数量
     *
     * @return Result对象，其中包含查询到的字典列表。如果查询成功，Result的success方法将返回true，否则返回false。
     */
    @Operation(summary = "查询字典表中记录的数量", description = "查询字典表中记录的数量")
    @Parameter(name = "activitySign", description = "活动报名实体对象", required = true)
    @GetMapping("/selectCount")
    public Result selectCount() {
        // 调用activitySignService中的selectCount方法查询字典表中的记录数
        List<Dict> dictList = activitySignService.selectCount();
        // 返回查询结果
        return Result.success(dictList);
    }
}
