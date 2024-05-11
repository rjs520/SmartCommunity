package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Building;

import java.util.List;

/**
 * 楼宇业务接口，提供楼宇的增删改查等操作
 *
 * @author rjs
 */
public interface BuildingService {
    /**
     * 添加楼宇
     *
     * @param building 待添加的楼宇对象，包含楼宇标题、内容、发布者、发布时间等所有必要的属性信息。
     */
    void add(Building building);

    /**
     * 通过ID删除楼宇
     *
     * @param id 需要删除的楼宇的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除楼宇
     *
     * @param ids 一个包含多个待删除楼宇ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新楼宇
     *
     * @param building 包含更新后楼宇属性的对象，其ID字段用于标识待更新的楼宇记录。
     */
    void updateById(Building building);

    /**
     * 根据ID查询楼宇
     *
     * @param id 要查询的楼宇的唯一标识符（ID）
     * @return 返回与指定ID匹配的楼宇对象，若未找到对应记录，则返回null。
     */
    Building selectById(Integer id);

    /**
     * 查询所有楼宇（可带条件过滤）
     *
     * @param building 可选的查询条件对象，用于设定筛选条件（如楼宇标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部楼宇。
     * @return 返回满足条件的所有楼宇对象组成的列表。
     */
    List<Building> selectAll(Building building);

    /**
     * 分页查询楼宇
     *
     * @param building 可选的查询条件对象，用于设定筛选条件（如楼宇标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）。
     * @param pageSize 每页显示的记录数。
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的楼宇列表以及总记录数、总页数等信息。
     */
    PageInfo<Building> selectPage(Building building, Integer pageNum, Integer pageSize);
}
