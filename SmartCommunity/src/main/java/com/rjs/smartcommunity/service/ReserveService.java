package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Reserve;

import java.util.List;

/**
 * 服务预约业务接口，提供服务预约的增删改查等操作
 *
 * @author rjs
 */
public interface ReserveService {
    /**
     * 添加服务预约
     *
     * @param reserve 待添加的服务预约对象，包含服务预约标题、内容、发布者、发布时间等所有必要的属性信息。
     */
    void add(Reserve reserve);

    /**
     * 通过ID删除服务预约
     *
     * @param id 需要删除的服务预约的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除服务预约
     *
     * @param ids 一个包含多个待删除服务预约ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新服务预约
     *
     * @param reserve 包含更新后服务预约属性的对象，其ID字段用于标识待更新的服务预约记录。
     */
    void updateById(Reserve reserve);

    /**
     * 根据ID查询服务预约
     *
     * @param id 要查询的服务预约的唯一标识符（ID）
     * @return 返回与指定ID匹配的服务预约对象，若未找到对应记录，则返回null。
     */
    Reserve selectById(Integer id);

    /**
     * 查询所有服务预约（可带条件过滤）
     *
     * @param reserve 可选的查询条件对象，用于设定筛选条件（如服务预约标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部服务预约。
     * @return 返回满足条件的所有服务预约对象组成的列表。
     */
    List<Reserve> selectAll(Reserve reserve);

    /**
     * 分页查询服务预约
     *
     * @param reserve 可选的查询条件对象，用于设定筛选条件（如服务预约标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）。
     * @param pageSize 每页显示的记录数。
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的服务预约列表以及总记录数、总页数等信息。
     */
    PageInfo<Reserve> selectPage(Reserve reserve, Integer pageNum, Integer pageSize);
}
