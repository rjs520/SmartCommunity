package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.enums.ResultCodeEnum;
import com.rjs.smartcommunity.common.enums.RoleEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.entity.ParkingSign;
import com.rjs.smartcommunity.exception.CustomException;
import com.rjs.smartcommunity.mapper.ParkingSignMapper;
import com.rjs.smartcommunity.service.ParkingSignService;
import com.rjs.smartcommunity.utils.TokenUtils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * ParkingSign服务实现类
 *
 * @author rjs
 */
@Service
public class ParkingSignServiceImpl implements ParkingSignService {
    @Resource private ParkingSignMapper parkingSignMapper;

    /**
     * 新增停车标志信息
     *
     * @param parkingSign 停车标志实体对象
     */
    @Override
    public void add(ParkingSign parkingSign) {
        // 设置当前日期为服务的创建时间
        parkingSign.setDate(DateUtil.today());
        // 查询用户是否已经活动报名
        ParkingSign sign =
                parkingSignMapper.selectByParkingIdAndUserId(
                        parkingSign.getParkingId(), parkingSign.getUserId());
        if (sign != null) {
            // 如果已经报名，则抛出异常
            throw new CustomException(ResultCodeEnum.APPOINTMENT_EXIST_ERROR);
        }
        parkingSignMapper.insert(parkingSign);
    }

    /**
     * 根据ID删除停车标志信息
     *
     * @param id 停车标志ID
     */
    @Override
    public void deleteById(Integer id) {
        parkingSignMapper.deleteById(id);
    }

    /**
     * 批量删除停车标志信息
     *
     * @param ids 停车标志ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            parkingSignMapper.deleteById(id);
        }
    }

    /**
     * 根据ID修改停车标志信息
     *
     * @param parkingSign 停车标志实体对象
     */
    @Override
    public void updateById(ParkingSign parkingSign) {
        parkingSignMapper.updateById(parkingSign);
    }

    /**
     * 根据ID查询停车标志信息
     *
     * @param id 停车标志ID
     * @return 返回查询到的停车标志实体对象
     */
    @Override
    public ParkingSign selectById(Integer id) {
        return parkingSignMapper.selectById(id);
    }

    /**
     * 查询所有停车标志信息
     *
     * @param parkingSign 停车标志实体对象，可用于过滤查询条件
     * @return 返回所有查询到的停车标志实体对象列表
     */
    @Override
    public List<ParkingSign> selectAll(ParkingSign parkingSign) {
        return parkingSignMapper.selectAll(parkingSign);
    }

    /**
     * 分页查询停车标志信息
     *
     * @param parkingSign 停车标志实体对象，可用于过滤查询条件
     * @param pageNum 页码
     * @param pageSize 每页显示数量
     * @return 返回分页后的停车标志信息列表
     */
    @Override
    public PageInfo<ParkingSign> selectPage(
            ParkingSign parkingSign, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Account currentUser = TokenUtils.getCurrentUser();
        if (currentUser.getRole().equals(RoleEnum.USER.name())) {
            // 普通用户只查询自己的数据
            parkingSign.setUserId(currentUser.getId());
        }
        List<ParkingSign> list = parkingSignMapper.selectAll(parkingSign);
        return PageInfo.of(list);
    }

    /**
     * 根据停车ID和用户ID选择停车标志。
     *
     * @param parkingId 停车场的ID，用于指定特定的停车场。
     * @param userId 用户的ID，用于指定特定的用户。
     * @return 返回与给定的停车ID和用户ID匹配的停车标志对象。如果没有匹配项，则返回null。
     */
    @Override
    public ParkingSign selectByParkingIdAndUserId(Integer parkingId, Integer userId) {
        // 通过调用mapper方法，根据给定的停车ID和用户ID查询并返回停车标志
        return parkingSignMapper.selectByParkingIdAndUserId(parkingId, userId);
    }

    /**
     * 查询并统计每个停车区域的停车标志数量。
     *
     * @return 返回包含每个停车区域名称及其对应标志数量的列表。
     */
    @Override
    public List<Dict> selectCount() {

        // 从数据库中查询所有停车标志
        List<ParkingSign> parkingSigns = parkingSignMapper.selectAll(null);

        // 过滤出状态为"审核通过"或"待审核"的停车标志
        parkingSigns =
                parkingSigns.stream()
                        .filter(
                                parkingSign ->
                                        "审核通过".equals(parkingSign.getStatus())
                                                || "待审核".equals(parkingSign.getStatus()))
                        .toList();

        // 提取所有停车区域的唯一地址
        Set<String> set =
                parkingSigns.stream()
                        .map(ParkingSign::getParkingAddress)
                        .collect(Collectors.toSet());

        List<Dict> list = CollUtil.newArrayList();

        // 统计每个停车区域的标志数量，并构建字典对象
        for (String name : set) {
            long count =
                    parkingSigns.stream()
                            .filter(parkingSign -> parkingSign.getParkingAddress().equals(name))
                            .count();

            Dict dict = Dict.create().set("name", name).set("value", count);
            list.add(dict);
        }

        return list;
    }
}
