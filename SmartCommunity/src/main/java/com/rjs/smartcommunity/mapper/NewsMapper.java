package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.News;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 公告数据操作接口，使用MyBatis的Mapper注解
 *
 * @author rjs
 */
@Mapper
public interface NewsMapper {

    /**
     * 新增咨询信息
     *
     * @param news 待插入的公告对象（News实体类）
     * @return 受影响的行数
     */
    int insert(News news);

    /**
     * 根据ID删除咨询信息
     *
     * @param id 公告ID
     * @return 受影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID更新咨询信息
     *
     * @param news 更新后的公告对象（News实体类）
     * @return 受影响的行数
     */
    int updateById(News news);

    /**
     * 根据ID查询咨询信息
     *
     * @param id 公告ID
     * @return 匹配的公告对象（News实体类），或null（无匹配记录）
     */
    News selectById(Integer id);

    /**
     * 查询所有咨询信息（可能带条件）
     *
     * @param news 可选条件对象（News实体类），用于传递查询条件（如按标题、创建时间等筛选）
     * @return 符合条件的公告列表（List<News>）
     */
    List<News> selectAll(News news);

    /**
     * 从数据库中选择顶部资讯。
     *
     * @param sort 指定排序方式的参数，用于确定资讯的排序方式。
     * @return 返回一个资讯列表，这些资讯根据指定的排序方式被选为顶部资讯。
     */
    List<News> selectTopNews(@Param("sort") String sort);

    /**
     * 更新计数。 该方法通过指定的ID来更新某个计数器的值。具体实现可能涉及数据库操作或其他数据存储的更新。
     *
     * @param id 要更新计数的唯一标识符。是一个整数类型。
     */
    void updateCount(Integer id);
}
