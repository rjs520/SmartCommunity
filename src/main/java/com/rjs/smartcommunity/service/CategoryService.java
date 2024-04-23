package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Category;

import java.util.List;

/**
 * 资讯分类信息服务类，封装资讯分类数据操作业务逻辑
 *
 * @author rjs
 */
public interface CategoryService {

    /**
     * 添加资讯分类信息
     *
     * @param category 待添加的资讯分类对象，应包含资讯分类标题、内容、发布者、发布时间等所有必要的属性信息
     */
    void add(Category category);

    /**
     * 通过ID删除资讯分类信息
     *
     * @param id 需要删除的资讯分类的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除资讯分类信息
     *
     * @param ids 一个包含多个待删除资讯分类ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新资讯分类信息
     *
     * @param category 包含更新后资讯分类属性的对象，其ID字段用于标识待更新的资讯分类记录
     */
    void updateById(Category category);

    /**
     * 根据ID查询资讯分类信息
     *
     * @param id 要查询的资讯分类的唯一标识符（ID）
     * @return 返回与指定ID匹配的资讯分类对象，若未找到对应记录，则返回null
     */
    Category selectById(Integer id);

    /**
     * 查询所有资讯分类信息（可带条件过滤）
     *
     * @param category 可选的查询条件对象，用于设定筛选条件（如资讯分类标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部资讯分类信息。
     * @return 返回满足条件的所有资讯分类对象组成的列表
     */
    List<Category> selectAll(Category category);

    /**
     * 分页查询资讯分类信息
     *
     * @param category 可选的查询条件对象，用于设定筛选条件（如资讯分类标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）
     * @param pageSize 每页显示的记录数
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的资讯分类列表以及总记录数、总页数等信息
     */
    PageInfo<Category> selectPage(Category category, Integer pageNum, Integer pageSize);
}
