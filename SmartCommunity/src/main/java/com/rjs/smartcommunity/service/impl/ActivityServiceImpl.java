package com.rjs.smartcommunity.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Activity;
import com.rjs.smartcommunity.mapper.ActivityMapper;
import com.rjs.smartcommunity.service.ActivityService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author rjs
 */
@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService {

    /** 活动数据操作接口注入 */
    @Resource private ActivityMapper activityMapper;

    /**
     * 新增活动
     *
     * @param activity 待添加的活动对象
     */
    @Override
    public void add(Activity activity) {
        // 执行数据插入操作
        activityMapper.insert(activity);
    }

    /**
     * 根据ID删除活动
     *
     * @param id 待删除活动的ID
     */
    @Override
    public void deleteById(Integer id) {
        activityMapper.deleteById(id);
    }

    /**
     * 批量删除活动
     *
     * @param ids 待删除活动的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            activityMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新活动
     *
     * @param activity 更新后的活动对象
     */
    @Override
    public void updateById(Activity activity) {
        activityMapper.updateById(activity);
    }

    /**
     * 根据ID查询活动
     *
     * @param id 待查询活动的ID
     * @return 匹配的活动对象，或null（无匹配记录）
     */
    @Override
    public Activity selectById(Integer id) {
        return activityMapper.selectById(id);
    }

    /**
     * 查询所有活动（可能带条件）
     *
     * @param activity 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）
     * @return 符合条件的活动列表
     */
    @Override
    public List<Activity> selectAll(Activity activity) {
        return activityMapper.selectAll(activity);
    }

    /**
     * 分页查询活动
     *
     * @param activity 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果对象，包含查询结果列表和分页信息
     */
    @Override
    public PageInfo<Activity> selectPage(Activity activity, Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<Activity> list = activityMapper.selectAll(activity);
        // 封装分页结果
        return PageInfo.of(list);
    }
}
