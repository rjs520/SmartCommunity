package com.rjs.smartcommunity.controller;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Result;
import com.rjs.smartcommunity.entity.Comment;
import com.rjs.smartcommunity.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论信息表前端操作接口
 *
 * @author rjs
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource private CommentService commentService;

    /**
     * 新增评论
     *
     * @param comment 包含评论内容的对象
     * @return 返回操作结果，成功则返回success结果
     */
    @PostMapping("/add")
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return Result.success();
    }

    /**
     * 根据ID删除评论
     *
     * @param id 需要删除的评论的ID
     * @return 返回操作结果，成功则返回success结果
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        commentService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除评论
     *
     * @param ids 需要删除的评论的ID列表
     * @return 返回操作结果，成功则返回success结果
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        commentService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 根据ID修改评论
     *
     * @param comment 包含更新后的评论信息的对象
     * @return 返回操作结果，成功则返回success结果
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Comment comment) {
        commentService.updateById(comment);
        return Result.success();
    }

    /**
     * 根据ID查询评论
     *
     * @param id 需要查询的评论的ID
     * @return 返回操作结果，包含查询到的评论对象
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Comment comment = commentService.selectById(id);
        return Result.success(comment);
    }

    /**
     * 查询所有评论
     *
     * @param comment 包含查询条件的对象（可选）
     * @return 返回操作结果，包含所有评论的列表
     */
    @GetMapping("/selectAll")
    public Result selectAll(Comment comment) {
        List<Comment> list = commentService.selectAll(comment);
        return Result.success(list);
    }

    /**
     * 分页查询评论
     *
     * @param comment 包含查询条件的对象（可选）
     * @param pageNum 请求的页码，默认为1
     * @param pageSize 每页显示的数量，默认为10
     * @return 返回操作结果，包含分页信息和评论列表
     */
    @GetMapping("/selectPage")
    public Result selectPage(
            Comment comment,
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Comment> page = commentService.selectPage(comment, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 根据给定的父ID和模块类型，查询并返回评论树。
     *
     * @param fid 父级评论的ID，用于指定评论树的根或特定节点。
     * @param module 模块类型，用于指定评论所属的模块。
     * @return Result对象，包含查询成功的评论列表。
     */
    @GetMapping("/selectTree/{fid}/{module}")
    public Result selectTree(@PathVariable Integer fid, @PathVariable String module) {
        // 通过父ID和模块类型，从评论服务中查询评论树
        List<Comment> list = commentService.selectTree(fid, module);
        // 将查询结果封装成成功结果并返回
        return Result.success(list);
    }
}
