package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Serve;

import java.util.List;

/**
 * 社区服务业务接口，提供社区服务的增删改查等操作
 *
 * @author rjs
 */
public interface ServeService {
    /**
     * 添加社区服务
     *
     * @param serve 待添加的社区服务对象，包含社区服务标题、内容、发布者、发布时间等所有必要的属性信息。
     */
    void add(Serve serve);

    /**
     * 通过ID删除社区服务
     *
     * @param id 需要删除的社区服务的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除社区服务
     *
     * @param ids 一个包含多个待删除社区服务ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新社区服务
     *
     * @param serve 包含更新后社区服务属性的对象，其ID字段用于标识待更新的社区服务记录。
     */
    void updateById(Serve serve);

    /**
     * 根据ID查询社区服务
     *
     * @param id 要查询的社区服务的唯一标识符（ID）
     * @return 返回与指定ID匹配的社区服务对象，若未找到对应记录，则返回null。
     */
    Serve selectById(Integer id);

    /**
     * 查询所有社区服务（可带条件过滤）
     *
     * @param serve 可选的查询条件对象，用于设定筛选条件（如社区服务标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部社区服务。
     * @return 返回满足条件的所有社区服务对象组成的列表。
     */
    List<Serve> selectAll(Serve serve);

    /**
     * 分页查询社区服务
     *
     * @param serve 可选的查询条件对象，用于设定筛选条件（如社区服务标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）。
     * @param pageSize 每页显示的记录数。
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的社区服务列表以及总记录数、总页数等信息。
     */
    PageInfo<Serve> selectPage(Serve serve, Integer pageNum, Integer pageSize);
}
