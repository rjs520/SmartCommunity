package com.rjs.smartcommunity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Category;
import com.rjs.smartcommunity.mapper.CategoryMapper;
import com.rjs.smartcommunity.service.CategoryService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author rjs
 */
@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {

    /** 资讯分类数据操作接口注入 */
    @Resource private CategoryMapper categoryMapper;

    /**
     * 新增资讯分类信息
     *
     * @param category 待添加的资讯分类对象
     */
    @Override
    public void add(Category category) {
        // 执行数据插入操作
        categoryMapper.insert(category);
    }

    /**
     * 根据ID删除资讯分类信息
     *
     * @param id 待删除资讯分类的ID
     */
    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }

    /**
     * 批量删除资讯分类信息
     *
     * @param ids 待删除资讯分类的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            categoryMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新资讯分类信息
     *
     * @param category 更新后的资讯分类对象
     */
    @Override
    public void updateById(Category category) {
        categoryMapper.updateById(category);
    }

    /**
     * 根据ID查询资讯分类信息
     *
     * @param id 待查询资讯分类的ID
     * @return 匹配的资讯分类对象，或null（无匹配记录）
     */
    @Override
    public Category selectById(Integer id) {
        return categoryMapper.selectById(id);
    }

    /**
     * 查询所有资讯分类信息（可能带条件）
     *
     * @param category 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）
     * @return 符合条件的资讯分类列表
     */
    @Override
    public List<Category> selectAll(Category category) {
        return categoryMapper.selectAll(category);
    }

    /**
     * 分页查询资讯分类信息
     *
     * @param category 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果对象，包含查询结果列表和分页信息
     */
    @Override
    public PageInfo<Category> selectPage(Category category, Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<Category> list = categoryMapper.selectAll(category);
        // 封装分页结果
        return PageInfo.of(list);
    }
}
