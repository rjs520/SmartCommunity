package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.Activity;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 公告数据操作接口，使用MyBatis的Mapper注解
 *
 * @author rjs
 */
@Mapper
public interface ActivityMapper {

    /**
     * 新增活动
     *
     * @param activity 待插入的公告对象（Activity实体类）
     * @return 受影响的行数
     */
    int insert(Activity activity);

    /**
     * 根据ID删除活动
     *
     * @param id 公告ID
     * @return 受影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID更新活动
     *
     * @param activity 更新后的公告对象（Activity实体类）
     * @return 受影响的行数
     */
    int updateById(Activity activity);

    /**
     * 根据ID查询活动
     *
     * @param id 公告ID
     * @return 匹配的公告对象（Activity实体类），或null（无匹配记录）
     */
    Activity selectById(Integer id);

    /**
     * 查询所有活动（可能带条件）
     *
     * @param activity 可选条件对象（Activity实体类），用于传递查询条件（如按标题、创建时间等筛选）
     * @return 符合条件的公告列表（List<Activity>）
     */
    List<Activity> selectAll(Activity activity);


}
