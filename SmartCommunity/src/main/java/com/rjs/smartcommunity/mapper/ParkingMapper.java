package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.Parking;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 操作parking相关数据接口
 *
 * @author rjs
 */
@Mapper
public interface ParkingMapper {

    /**
     * 新增停车记录
     *
     * @param parking 停车实体对象
     * @return 返回影响的行数
     */
    int insert(Parking parking);

    /**
     * 根据ID删除停车记录
     *
     * @param id 停车记录的ID
     * @return 返回影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID修改停车记录
     *
     * @param parking 停车实体对象，包含要修改的数据
     * @return 返回影响的行数
     */
    int updateById(Parking parking);

    /**
     * 根据ID查询停车记录
     *
     * @param id 停车记录的ID
     * @return 返回匹配的停车实体对象，如果没有找到返回null
     */
    Parking selectById(Integer id);

    /**
     * 查询所有停车记录
     *
     * @param parking 可选的停车实体对象，用于可能的过滤条件（本接口未实现过滤功能，仅提供扩展可能性）
     * @return 返回所有停车记录的列表
     */
    List<Parking> selectAll(Parking parking);
}
