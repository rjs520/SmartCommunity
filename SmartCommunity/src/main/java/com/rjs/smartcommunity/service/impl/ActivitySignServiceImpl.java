package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.date.DateUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.enums.ResultCodeEnum;
import com.rjs.smartcommunity.common.enums.RoleEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.entity.ActivitySign;
import com.rjs.smartcommunity.exception.CustomException;
import com.rjs.smartcommunity.mapper.ActivitySignMapper;
import com.rjs.smartcommunity.service.ActivitySignService;

import com.rjs.smartcommunity.utils.TokenUtils;
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
        // 查询用户是否已经活动报名
        ActivitySign sign =
                activitySignMapper.selectByActivityIdAndUserId(
                        activitySign.getActivityId(), activitySign.getUserId());
        if (sign != null) {
            // 如果已经报名，则抛出异常
            throw new CustomException(ResultCodeEnum.SIGN_EXIST_ERROR);
        }
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
        Account currentUser = TokenUtils.getCurrentUser();
        if (currentUser.getRole().equals(RoleEnum.USER.name())) {
            // 普通用户只查询自己的数据
            activitySign.setUserId(currentUser.getId());
        }
        // 初始化分页插件
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<ActivitySign> list = activitySignMapper.selectAll(activitySign);
        // 封装分页结果
        return PageInfo.of(list);
    }

    /**
     * 根据活动ID和用户ID选择活动信息。 该方法会查询数据库中与给定活动ID和用户ID匹配的活动记录。
     *
     * @param activityId 活动的唯一标识符。
     * @param userId 用户的唯一标识符。 该方法没有返回值，它只是触发了对数据库的查询操作，并将结果传递给了调用者或进一步地处理逻辑。
     * @return 匹配的活动报名对象，如果不存在则返回null。
     */
    @Override
    public ActivitySign selectByActivityIdAndUserId(Integer activityId, Integer userId) {
        // 调用activityMapper，查询指定活动ID和用户ID的活动信息
        return activitySignMapper.selectByActivityIdAndUserId(activityId, userId);
    }
}