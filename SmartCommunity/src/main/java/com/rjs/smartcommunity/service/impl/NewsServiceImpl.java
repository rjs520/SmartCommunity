package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.date.DateUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.News;
import com.rjs.smartcommunity.mapper.NewsMapper;
import com.rjs.smartcommunity.service.NewsService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author rjs
 */
@Service("NewsService")
public class NewsServiceImpl implements NewsService {

    /** 资讯数据操作接口注入 */
    @Resource private NewsMapper newsMapper;

    /**
     * 新增资讯信息
     *
     * @param news 待添加的资讯对象
     */
    @Override
    public void add(News news) {
        // 设置当前日期为资讯创建时间
        news.setTime(DateUtil.now());
        // 执行数据插入操作
        newsMapper.insert(news);
    }

    /**
     * 根据ID删除资讯信息
     *
     * @param id 待删除资讯的ID
     */
    @Override
    public void deleteById(Integer id) {
        newsMapper.deleteById(id);
    }

    /**
     * 批量删除资讯信息
     *
     * @param ids 待删除资讯的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            newsMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新资讯信息
     *
     * @param news 更新后的资讯对象
     */
    @Override
    public void updateById(News news) {
        newsMapper.updateById(news);
    }

    /**
     * 根据ID查询资讯信息
     *
     * @param id 待查询资讯的ID
     * @return 匹配的资讯对象，或null（无匹配记录）
     */
    @Override
    public News selectById(Integer id) {
        return newsMapper.selectById(id);
    }

    /**
     * 查询所有资讯信息（可能带条件）
     *
     * @param news 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）
     * @return 符合条件的资讯列表
     */
    @Override
    public List<News> selectAll(News news) {
        return newsMapper.selectAll(news);
    }

    /**
     * 分页查询资讯信息
     *
     * @param news 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果对象，包含查询结果列表和分页信息
     */
    @Override
    public PageInfo<News> selectPage(News news, Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<News> list = newsMapper.selectAll(news);
        // 封装分页结果
        return PageInfo.of(list);
    }

    /**
     * 从资讯数据库中根据指定排序方式选择顶部资讯。
     *
     * @param sort 指定的排序方式，用于筛选顶部资讯。不同的排序方式将影响返回的资讯列表。
     * @return 返回一个资讯列表，包含根据指定排序方式选择的顶部资讯。返回的列表类型为News类型的集合。
     */
    @Override
    public List<News> selectTopNews(String sort) {
        // 调用newsMapper中的selectTopNews方法，根据指定排序方式选择顶部资讯
        return newsMapper.selectTopNews(sort);
    }
}
