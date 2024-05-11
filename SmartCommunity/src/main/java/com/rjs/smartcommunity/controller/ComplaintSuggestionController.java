package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.ComplaintSuggestion;
import com.rjs.smartcommunity.service.ComplaintSuggestionService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 投诉与建议前端操作接口
 *
 * @author rjs
 */
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
