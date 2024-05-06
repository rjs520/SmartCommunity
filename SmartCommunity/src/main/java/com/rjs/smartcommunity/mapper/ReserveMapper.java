package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.Reserve;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作reserve相关数据接口
 *
 * @author rjs
 */
@Mapper
public interface ReserveMapper {

    /**
     * 新增reserve记录
     *
     * @param reserve 需要新增的服务预约实体
     * @return 返回影响的行数
     */
    int insert(Reserve reserve);

    /**
     * 根据ID删除服务预约记录
     *
     * @param id 需要删除的服务预约的ID
     * @return 返回影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID修改服务预约记录
     *
     * @param reserve 需要修改的服务预约实体，其中包含了修改后的数据
     * @return 返回影响的行数
     */
    int updateById(Reserve reserve);

    /**
     * 根据ID查询服务预约记录
     *
     * @param id 需要查询的服务预约的ID
     * @return 返回查询到的服务预约实体，如果不存在则返回null
     */
    Reserve selectById(Integer id);

    /**
     * 查询所有服务预约记录
     *
     * @param reserve 可选参数，用于可能的查询条件构建。如果不提供任何条件，则查询全部
     * @return 返回服务预约记录的列表
     */
    List<Reserve> selectAll(Reserve reserve);
}
