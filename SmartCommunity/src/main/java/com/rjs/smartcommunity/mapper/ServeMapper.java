package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.Serve;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * Serve实体的数据映射接口。
 *
 * @author rjs
 */
@Mapper
public interface ServeMapper {
    /**
     * 新增Serve记录。
     *
     * @param serve Serve实体对象，包含新增记录的信息。
     * @return 返回影响的行数，通常为1（若插入成功）。
     */
    int insert(Serve serve);

    /**
     * 根据ID删除Serve记录。
     *
     * @param id 要删除的记录的ID。
     * @return 返回影响的行数，通常为1（若删除成功）。
     */
    int deleteById(Integer id);

    /**
     * 根据ID修改Serve记录。
     *
     * @param serve 包含修改后信息的Serve实体对象。
     * @return 返回影响的行数，通常为1（若修改成功）。
     */
    int updateById(Serve serve);

    /**
     * 根据ID查询Serve记录。
     *
     * @param id 要查询的记录的ID。
     * @return 返回匹配的Serve实体对象，如果不存在则返回null。
     */
    Serve selectById(Integer id);

    /**
     * 查询所有Serve记录。
     *
     * @param serve 可选的Serve实体对象，可用于过滤查询结果（目前方法实现可能不支持过滤）。
     * @return 返回包含所有Serve记录的列表。
     */
    List<Serve> selectAll(Serve serve);
}
