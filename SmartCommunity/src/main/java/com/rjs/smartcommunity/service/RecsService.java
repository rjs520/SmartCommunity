package com.rjs.smartcommunity.service;

import cn.hutool.core.lang.Dict;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Recs;

import java.util.List;

/**
 * 反馈与建议业务接口，提供反馈与建议的增删改查等操作
 *
 * @author rjs
 */
public interface RecsService {
    /**
     * 添加反馈与建议的功能方法。 该方法接收一个包含多个反馈与建议对象的列表，将这些反馈与建议信息添加到系统中。
     *
     * @param recsList 待添加的反馈与建议对象列表，每个对象应包含反馈与建议的标题、内容、发布者、发布时间等所有必要的属性信息。
     *     列表中的每个对象都会被添加到系统中的反馈与建议库中。
     *     <p>注意：该方法没有返回值，即添加操作完成后不返回任何结果。
     */
    void add(List<Recs> recsList);

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
     * @param recs 包含更新后反馈与建议属性的对象，其ID字段用于标识待更新的反馈与建议记录。
     */
    void updateById(Recs recs);

    /**
     * 根据ID查询反馈与建议
     *
     * @param id 要查询的反馈与建议的唯一标识符（ID）
     * @return 返回与指定ID匹配的反馈与建议对象，若未找到对应记录，则返回null。
     */
    Recs selectById(Integer id);

    /**
     * 查询所有反馈与建议（可带条件过滤）
     *
     * @param recs 可选的查询条件对象，用于设定筛选条件（如反馈与建议标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部反馈与建议。
     * @return 返回满足条件的所有反馈与建议对象组成的列表。
     */
    List<Recs> selectAll(Recs recs);

    /**
     * 分页查询反馈与建议
     *
     * @param recs 可选的查询条件对象，用于设定筛选条件（如反馈与建议标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）。
     * @param pageSize 每页显示的记录数。
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的反馈与建议列表以及总记录数、总页数等信息。
     */
    PageInfo<Recs> selectPage(Recs recs, Integer pageNum, Integer pageSize);

    /**
     * 查询并返回字典表中的所有记录数量。
     *
     * @return List<Dict> 返回一个包含字典表中所有记录的列表。
     */
    List<Dict> selectCount();

    /**
     * 根据客服ID和用户ID选择相应的记录。
     *
     * @param csId 客服ID，用于查询与特定客服相关的记录。
     * @param userId 用户ID，用于查询与特定用户相关的记录。
     * @return 返回一个记录列表，这些记录与给定的客服ID和用户ID匹配。
     */
    List<Recs> selectByCsIdAndUserId(Integer csId, Integer userId);
}
