package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.NurseHouse;

import java.util.List;

/**
 * 养老院业务接口，提供养老院的增删改查等操作
 *
 * @author rjs
 */
public interface NurseHouseService {
    /**
     * 添加养老院
     *
     * @param nurseHouse 待添加的养老院对象，包含养老院标题、内容、发布者、发布时间等所有必要的属性信息。
     */
    void add(NurseHouse nurseHouse);

    /**
     * 通过ID删除养老院
     *
     * @param id 需要删除的养老院的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除养老院
     *
     * @param ids 一个包含多个待删除养老院ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新养老院
     *
     * @param nurseHouse 包含更新后养老院属性的对象，其ID字段用于标识待更新的养老院记录。
     */
    void updateById(NurseHouse nurseHouse);

    /**
     * 根据ID查询养老院
     *
     * @param id 要查询的养老院的唯一标识符（ID）
     * @return 返回与指定ID匹配的养老院对象，若未找到对应记录，则返回null。
     */
    NurseHouse selectById(Integer id);

    /**
     * 查询所有养老院（可带条件过滤）
     *
     * @param nurseHouse 可选的查询条件对象，用于设定筛选条件（如养老院标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部养老院。
     * @return 返回满足条件的所有养老院对象组成的列表。
     */
    List<NurseHouse> selectAll(NurseHouse nurseHouse);

    /**
     * 分页查询养老院
     *
     * @param nurseHouse 可选的查询条件对象，用于设定筛选条件（如养老院标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）。
     * @param pageSize 每页显示的记录数。
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的养老院列表以及总记录数、总页数等信息。
     */
    PageInfo<NurseHouse> selectPage(NurseHouse nurseHouse, Integer pageNum, Integer pageSize);

    /**
     * 更新计数。 该方法通过指定的ID来更新某个计数器的值。具体实现可能涉及数据库操作或其他数据存储的更新。
     *
     * @param id 要更新计数的唯一标识符。是一个整数类型。
     */
    void updateCount(Integer id);
}
