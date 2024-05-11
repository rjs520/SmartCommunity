package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.Building;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作building相关数据接口
 *
 * @author rjs
 */
@Mapper
public interface BuildingMapper {

    /**
     * 新增Building记录
     *
     * @param building Building实体，包含新增的数据
     * @return int 返回影响的行数，通常为1表示新增成功
     */
    int insert(Building building);

    /**
     * 根据ID删除Building记录
     *
     * @param id Building的ID，用于指定要删除的记录
     * @return int 返回影响的行数，通常为1表示删除成功
     */
    int deleteById(Integer id);

    /**
     * 根据ID修改Building记录
     *
     * @param building Building实体，包含要修改的数据
     * @return int 返回影响的行数，通常为1表示修改成功
     */
    int updateById(Building building);

    /**
     * 根据ID查询Building记录
     *
     * @param id Building的ID，用于指定要查询的记录
     * @return Building 返回查询到的Building实体，如果不存在则返回null
     */
    Building selectById(Integer id);

    /**
     * 查询所有Building记录
     *
     * @param building Building实体，可选参数，用于可能的查询条件构建
     * @return List<Building> 返回Building实体列表，可能为空集合
     */
    List<Building> selectAll(Building building);
}
