package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.News;

import java.util.List;

/**
 * 咨询信息服务类，封装公告数据操作业务逻辑
 *
 * @author rjs
 */
public interface NewsService {

    /**
     * 添加咨询信息
     *
     * @param news 待添加的公告对象，应包含公告标题、内容、发布者、发布时间等所有必要的属性信息
     */
    void add(News news);

    /**
     * 通过ID删除咨询信息
     *
     * @param id 需要删除的公告的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除咨询信息
     *
     * @param ids 一个包含多个待删除公告ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新咨询信息
     *
     * @param news 包含更新后公告属性的对象，其ID字段用于标识待更新的公告记录
     */
    void updateById(News news);

    /**
     * 根据ID查询咨询信息
     *
     * @param id 要查询的公告的唯一标识符（ID）
     * @return 返回与指定ID匹配的公告对象，若未找到对应记录，则返回null
     */
    News selectById(Integer id);

    /**
     * 查询所有咨询信息（可带条件过滤）
     *
     * @param news 可选的查询条件对象，用于设定筛选条件（如公告标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部咨询信息。
     * @return 返回满足条件的所有公告对象组成的列表
     */
    List<News> selectAll(News news);

    /**
     * 分页查询咨询信息
     *
     * @param news 可选的查询条件对象，用于设定筛选条件（如公告标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）
     * @param pageSize 每页显示的记录数
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的公告列表以及总记录数、总页数等信息
     */
    PageInfo<News> selectPage(News news, Integer pageNum, Integer pageSize);

    /**
     * 从数据库中选择顶部资讯。
     *
     * @param sort 指定排序方式，可以是按照发布日期、点击率等标准。
     * @return 返回一个资讯列表，这些资讯根据指定的排序方式被认为是顶部资讯。
     */
    List<News> selectTopNews(String sort);

    /**
     * 更新计数。 该方法通过指定的ID来更新某个计数器的值。具体实现可能涉及数据库操作或其他数据存储的更新。
     *
     * @param id 要更新计数的唯一标识符。是一个整数类型。
     */
    void updateCount(Integer id);
}
