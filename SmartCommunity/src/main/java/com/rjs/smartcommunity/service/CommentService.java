package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Comment;

import java.util.List;

/**
 * 评论业务接口，提供评论的增删改查等操作
 *
 * @author rjs
 */
public interface CommentService {
    /**
     * 添加评论
     *
     * @param comment 待添加的评论对象，包含评论标题、内容、发布者、发布时间等所有必要的属性信息。
     */
    void add(Comment comment);

    /**
     * 通过ID删除评论
     *
     * @param id 需要删除的评论的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除评论
     *
     * @param ids 一个包含多个待删除评论ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新评论
     *
     * @param comment 包含更新后评论属性的对象，其ID字段用于标识待更新的评论记录。
     */
    void updateById(Comment comment);

    /**
     * 根据ID查询评论
     *
     * @param id 要查询的评论的唯一标识符（ID）
     * @return 返回与指定ID匹配的评论对象，若未找到对应记录，则返回null。
     */
    Comment selectById(Integer id);

    /**
     * 查询所有评论（可带条件过滤）
     *
     * @param comment 可选的查询条件对象，用于设定筛选条件（如评论标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部评论。
     * @return 返回满足条件的所有评论对象组成的列表。
     */
    List<Comment> selectAll(Comment comment);

    /**
     * 分页查询评论
     *
     * @param comment 可选的查询条件对象，用于设定筛选条件（如评论标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）。
     * @param pageSize 每页显示的记录数。
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的评论列表以及总记录数、总页数等信息。
     */
    PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize);

    List<Comment> selectTree(Integer fid, String module);
}
