package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.date.DateUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.ActivitySign;
import com.rjs.smartcommunity.mapper.ActivitySignMapper;
import com.rjs.smartcommunity.service.ActivitySignService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 活动报名业务逻辑实现类
 *
 * @author rjs
 */
@Service("ActivitySignService")
public class ActivitySignServiceImpl implements ActivitySignService {

    /** 活动报名数据操作接口 */
    @Resource private ActivitySignMapper activitySignMapper;

    /**
     * 新增活动报名
     *
     * @param activitySign 待添加的活动报名对象，包含服务的详细信息。
     */
    @Override
    public void add(ActivitySign activitySign) {
        // 设置当前日期为服务的创建时间
        activitySign.setDate(DateUtil.today());
        // 执行数据插入操作
        activitySignMapper.insert(activitySign);
    }

    /**
     * 根据ID删除活动报名
     *
     * @param id 待删除活动报名的ID
     */
    @Override
    public void deleteById(Integer id) {
        activitySignMapper.deleteById(id);
    }

    /**
     * 批量删除活动报名
     *
     * @param ids 待删除活动报名的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        // 遍历ID集合，逐个执行删除操作
        for (Integer id : ids) {
            activitySignMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新活动报名
     *
     * @param activitySign 更新后的活动报名对象，包含更新后的服务信息。
     */
    @Override
    public void updateById(ActivitySign activitySign) {
        activitySignMapper.updateById(activitySign);
    }

    /**
     * 根据ID查询活动报名
     *
     * @param id 待查询活动报名的ID
     * @return 匹配的活动报名对象，如果不存在则返回null。
     */
    @Override
    public ActivitySign selectById(Integer id) {
        return activitySignMapper.selectById(id);
    }

    /**
     * 查询所有活动报名（可能带条件）
     *
     * @param activitySign 可选条件对象，用于传递查询条件，如标题、创建时间等。
     * @return 符合条件的活动报名列表。
     */
    @Override
    public List<ActivitySign> selectAll(ActivitySign activitySign) {
        return activitySignMapper.selectAll(activitySign);
    }

    /**
     * 分页查询活动报名
     *
     * @param activitySign 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）。
     * @param pageNum 页码。
     * @param pageSize 每页大小。
     * @return 分页结果对象，包含查询结果列表和分页信息。
     */
    @Override
    public PageInfo<ActivitySign> selectPage(
            ActivitySign activitySign, Integer pageNum, Integer pageSize) {
        // 初始化分页插件
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<ActivitySign> list = activitySignMapper.selectAll(activitySign);
        // 封装分页结果
        return PageInfo.of(list);
    }
}
