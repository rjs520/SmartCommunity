package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.Notice;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 公告数据操作接口，使用MyBatis的Mapper注解
 *
 * @author rjs
 */
@Mapper
public interface NoticeMapper {

    /**
     * 新增公告信息
     *
     * @param notice 待插入的公告对象（Notice实体类）
     * @return 受影响的行数
     */
    int insert(Notice notice);

    /**
     * 根据ID删除公告信息
     *
     * @param id 公告ID
     * @return 受影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID更新公告信息
     *
     * @param notice 更新后的公告对象（Notice实体类）
     * @return 受影响的行数
     */
    int updateById(Notice notice);

    /**
     * 根据ID查询公告信息
     *
     * @param id 公告ID
     * @return 匹配的公告对象（Notice实体类），或null（无匹配记录）
     */
    Notice selectById(Integer id);

    /**
     * 查询所有公告信息（可能带条件）
     *
     * @param notice 可选条件对象（Notice实体类），用于传递查询条件（如按标题、创建时间等筛选）
     * @return 符合条件的公告列表（List<Notice>）
     */
    List<Notice> selectAll(Notice notice);
}
