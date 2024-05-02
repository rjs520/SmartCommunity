package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Parking;

import java.util.List;

/**
 * 车位服务类，封装车位数据操作业务逻辑
 *
 * @author rjs
 */
public interface ParkingService {

    /**
     * 添加车位
     *
     * @param parking 待添加的车位对象，应包含车位标题、内容、发布者、发布时间等所有必要的属性信息
     */
    void add(Parking parking);

    /**
     * 通过ID删除车位
     *
     * @param id 需要删除的车位的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除车位
     *
     * @param ids 一个包含多个待删除车位ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新车位
     *
     * @param parking 包含更新后车位属性的对象，其ID字段用于标识待更新的车位记录
     */
    void updateById(Parking parking);

    /**
     * 根据ID查询车位
     *
     * @param id 要查询的车位的唯一标识符（ID）
     * @return 返回与指定ID匹配的车位对象，若未找到对应记录，则返回null
     */
    Parking selectById(Integer id);

    /**
     * 查询所有车位（可带条件过滤）
     *
     * @param parking 可选的查询条件对象，用于设定筛选条件（如车位标题、发布者、发布时间范围等）。如果传入null或空对象，表示查询全部车位。
     * @return 返回满足条件的所有车位对象组成的列表
     */
    List<Parking> selectAll(Parking parking);

    /**
     * 分页查询车位
     *
     * @param parking 可选的查询条件对象，用于设定筛选条件（如车位标题、发布者、发布时间范围等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）
     * @param pageSize 每页显示的记录数
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的车位列表以及总记录数、总页数等信息
     */
    PageInfo<Parking> selectPage(Parking parking, Integer pageNum, Integer pageSize);
}
