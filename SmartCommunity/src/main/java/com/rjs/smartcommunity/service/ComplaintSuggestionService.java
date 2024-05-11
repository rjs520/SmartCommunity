package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.ComplaintSuggestion;

import java.util.List;

/**
 * 反馈与建议业务接口，提供反馈与建议的增删改查等操作
 *
 * @author rjs
 */
public interface ComplaintSuggestionService {
    /**
     * 添加反馈与建议
     *
     * @param complaintSuggestion 待添加的反馈与建议对象，包含反馈与建议标题、内容、发布者、发布时间等所有必要的属性信息。
     */
    void add(ComplaintSuggestion complaintSuggestion);

    /**
     * 通过ID删除反馈与建议
     *
     * @param id 需要删除的反馈与建议的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除反馈与建议
     *
     * @param ids 一个包含多个待删除反馈与建议ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新反馈与建议
     *
     * @param complaintSuggestion 包含更新后反馈与建议属性的对象，其ID字段用于标识待更新的反馈与建议记录。
     */
    void updateById(ComplaintSuggestion complaintSuggestion);

    /**
     * 根据ID查询反馈与建议
     *
     * @param id 要查询的反馈与建议的唯一标识符（ID）
     * @return 返回与指定ID匹配的反馈与建议对象，若未找到对应记录，则返回null。
     */
    ComplaintSuggestion selectById(Integer id);

    /**
     * 查询所有反馈与建议（可带条件过滤）
     *
     * @param complaintSuggestion 可选的查询条件对象，用于设定筛选条件（如反馈与建议标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部反馈与建议。
     * @return 返回满足条件的所有反馈与建议对象组成的列表。
     */
    List<ComplaintSuggestion> selectAll(ComplaintSuggestion complaintSuggestion);

    /**
     * 分页查询反馈与建议
     *
     * @param complaintSuggestion
     *     可选的查询条件对象，用于设定筛选条件（如反馈与建议标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）。
     * @param pageSize 每页显示的记录数。
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的反馈与建议列表以及总记录数、总页数等信息。
     */
    PageInfo<ComplaintSuggestion> selectPage(
            ComplaintSuggestion complaintSuggestion, Integer pageNum, Integer pageSize);
}
