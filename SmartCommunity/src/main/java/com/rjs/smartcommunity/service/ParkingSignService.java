package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.ParkingSign;

import java.util.List;

/**
 * 车位预约业务处理接口
 *
 * @author rjs
 */
public interface ParkingSignService {
    /**
     * 新增车位预约信息
     *
     * @param parkingSign 车位预约实体对象
     */
    void add(ParkingSign parkingSign);

    /**
     * 根据ID删除车位预约信息
     *
     * @param id 预约信息ID
     */
    void deleteById(Integer id);

    /**
     * 批量根据ID删除车位预约信息
     *
     * @param ids 预约信息ID集合
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 根据ID修改车位预约信息
     *
     * @param parkingSign 车位预约实体对象
     */
    void updateById(ParkingSign parkingSign);

    /**
     * 根据ID查询车位预约信息
     *
     * @param id 预约信息ID
     * @return 返回查询到的车位预约实体对象
     */
    ParkingSign selectById(Integer id);

    /**
     * 查询所有车位预约信息
     *
     * @param parkingSign 车位预约实体对象，可用来进行条件查询
     * @return 返回所有查询到的车位预约实体对象列表
     */
    List<ParkingSign> selectAll(ParkingSign parkingSign);

    /**
     * 分页查询车位预约信息
     *
     * @param parkingSign 车位预约实体对象，可用来进行条件查询
     * @param pageNum 查询页码
     * @param pageSize 每页显示数量
     * @return 返回分页查询结果的 PageInfo 对象，包含车位预约信息
     */
    PageInfo<ParkingSign> selectPage(ParkingSign parkingSign, Integer pageNum, Integer pageSize);

    /**
     * 根据停车ID和用户ID选择停车标志。
     *
     * @param parkingId 停车场的唯一标识符。
     * @param userId 用户的唯一标识符。
     * @return 返回与给定的停车ID和用户ID匹配的停车标志。如果找不到匹配项，则返回null。
     */
    ParkingSign selectByParkingIdAndUserId(Integer parkingId, Integer userId);
}
