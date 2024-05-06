package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.ActivitySign;
import java.util.List;

/**
 * 活动报名业务接口，提供活动报名的增删改查等操作
 *
 * @author rjs
 */
public interface ActivitySignService {
    /**
     * 添加活动报名
     *
     * @param activitySign 待添加的活动报名对象，包含活动报名标题、内容、发布者、发布时间等所有必要的属性信息。
     */
    void add(ActivitySign activitySign);

    /**
     * 通过ID删除活动报名
     *
     * @param id 需要删除的活动报名的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除活动报名
     *
     * @param ids 一个包含多个待删除活动报名ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新活动报名
     *
     * @param activitySign 包含更新后活动报名属性的对象，其ID字段用于标识待更新的活动报名记录。
     */
    void updateById(ActivitySign activitySign);

    /**
     * 根据ID查询活动报名
     *
     * @param id 要查询的活动报名的唯一标识符（ID）
     * @return 返回与指定ID匹配的活动报名对象，若未找到对应记录，则返回null。
     */
    ActivitySign selectById(Integer id);

    /**
     * 查询所有活动报名（可带条件过滤）
     *
     * @param activitySign 可选的查询条件对象，用于设定筛选条件（如活动报名标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部活动报名。
     * @return 返回满足条件的所有活动报名对象组成的列表。
     */
    List<ActivitySign> selectAll(ActivitySign activitySign);

    /**
     * 分页查询活动报名
     *
     * @param activitySign 可选的查询条件对象，用于设定筛选条件（如活动报名标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）。
     * @param pageSize 每页显示的记录数。
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的活动报名列表以及总记录数、总页数等信息。
     */
    PageInfo<ActivitySign> selectPage(ActivitySign activitySign, Integer pageNum, Integer pageSize);

    /**
     * 根据活动ID和用户ID进行选择操作的函数。 该方法用于根据提供的活动ID和用户ID在数据库或其他数据存储中查询相关数据或执行特定操作。
     * 注意：此方法没有返回值，它可能是为了更新数据库或执行某些不返回结果的业务逻辑。
     *
     * @param activityId 活动的唯一标识符。用于指定特定的活动。
     * @param userId 用户的唯一标识符。用于指定特定的用户。
     * @return 返回一个ActivitySign对象，包含指定活动ID和用户ID的报名信息。
     */
    ActivitySign selectByActivityIdAndUserId(Integer activityId, Integer userId);
}
