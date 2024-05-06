package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.ActivitySign;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 操作activity_sign相关数据接口
 *
 * @author rjs
 */
@Mapper
public interface ActivitySignMapper {

    /**
     * 新增activity_sign记录
     *
     * @param activitySign 包含activity_sign信息的对象
     * @return 返回影响的行数
     */
    int insert(ActivitySign activitySign);

    /**
     * 根据ID删除activity_sign记录
     *
     * @param id 要删除的记录的ID
     * @return 返回影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID修改activity_sign记录
     *
     * @param activitySign 包含修改后的activity_sign信息的对象
     * @return 返回影响的行数
     */
    int updateById(ActivitySign activitySign);

    /**
     * 根据ID查询activity_sign记录
     *
     * @param id 要查询的记录的ID
     * @return 返回查询到的activity_sign对象
     */
    ActivitySign selectById(Integer id);

    /**
     * 查询所有activity_sign记录
     *
     * @param activitySign 可以根据此对象的属性进行筛选查询，也可以留空查询所有
     * @return 返回activity_sign记录的列表
     */
    List<ActivitySign> selectAll(ActivitySign activitySign);

    /**
     * 根据活动ID和用户ID查询活动报名信息。
     *
     * @param activityId 活动的唯一标识符，用于指定特定的活动。
     * @param userId 用户的唯一标识符，用于指定特定的用户。
     * @return 返回一个ActivitySign对象，包含指定活动ID和用户ID的报名信息。
     */
    ActivitySign selectByActivityIdAndUserId(
            @Param("activityId") Integer activityId, @Param("userId") Integer userId);
}
