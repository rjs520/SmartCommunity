package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Notice;

import java.util.List;

/**
 * 公告信息服务类，封装公告数据操作业务逻辑
 *
 * @author rjs
 */
public interface NoticeService {

    /**
     * 添加公告信息
     *
     * @param notice 待添加的公告对象，应包含公告标题、内容、发布者、发布时间等所有必要的属性信息
     */
    void add(Notice notice);

    /**
     * 通过ID删除公告信息
     *
     * @param id 需要删除的公告的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除公告信息
     *
     * @param ids 一个包含多个待删除公告ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新公告信息
     *
     * @param notice 包含更新后公告属性的对象，其ID字段用于标识待更新的公告记录
     */
    void updateById(Notice notice);

    /**
     * 根据ID查询公告信息
     *
     * @param id 要查询的公告的唯一标识符（ID）
     * @return 返回与指定ID匹配的公告对象，若未找到对应记录，则返回null
     */
    Notice selectById(Integer id);

    /**
     * 查询所有公告信息（可带条件过滤）
     *
     * @param notice 可选的查询条件对象，用于设定筛选条件（如公告标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部公告信息。
     * @return 返回满足条件的所有公告对象组成的列表
     */
    List<Notice> selectAll(Notice notice);

    /**
     * 分页查询公告信息
     *
     * @param notice 可选的查询条件对象，用于设定筛选条件（如公告标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）
     * @param pageSize 每页显示的记录数
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的公告列表以及总记录数、总页数等信息
     */
    PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize);
}
