package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.ParkingSign;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 操作parking_sign相关数据接口
 *
 * @author rjs
 */
@Mapper
public interface ParkingSignMapper {

    /**
     * 新增停车标志信息
     *
     * @param parkingSign 停车标志实体对象
     * @return 返回影响的行数
     */
    int insert(ParkingSign parkingSign);

    /**
     * 根据ID删除停车标志信息
     *
     * @param id 停车标志的ID
     * @return 返回影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID修改停车标志信息
     *
     * @param parkingSign 停车标志实体对象
     * @return 返回影响的行数
     */
    int updateById(ParkingSign parkingSign);

    /**
     * 根据ID查询停车标志信息
     *
     * @param id 停车标志的ID
     * @return 返回查询到的停车标志实体对象
     */
    ParkingSign selectById(Integer id);

    /**
     * 查询所有停车标志信息
     *
     * @param parkingSign 停车标志实体对象，可用于过滤查询条件
     * @return 返回所有查询到的停车标志实体列表
     */
    List<ParkingSign> selectAll(ParkingSign parkingSign);

    /**
     * 根据停车ID和用户ID选择停车标志。
     *
     * @param parkingId 停车场的唯一标识符。
     * @param userId 用户的唯一标识符。
     * @return 返回与给定的停车ID和用户ID匹配的停车标志对象。如果没有找到匹配项，则返回null。
     */
    ParkingSign selectByParkingIdAndUserId(
            @Param("parkingId") Integer parkingId, @Param("userId") Integer userId);
}
