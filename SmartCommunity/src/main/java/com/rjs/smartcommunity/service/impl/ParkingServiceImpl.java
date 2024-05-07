package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.date.DateUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.enums.ParkingStatusEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.entity.Parking;
import com.rjs.smartcommunity.entity.ParkingSign;
import com.rjs.smartcommunity.mapper.ParkingMapper;
import com.rjs.smartcommunity.service.ParkingService;
import com.rjs.smartcommunity.service.ParkingSignService;
import com.rjs.smartcommunity.utils.TokenUtils;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author rjs
 */
@Service("ParkingService")
public class ParkingServiceImpl implements ParkingService {

    /** 车位数据操作接口注入 */
    @Resource private ParkingMapper parkingMapper;

    /**
     * 通过@Resource注解自动注入ParkingSignService实例。
     * 该注入方式基于Java标准的JSR-250规范，它可以将符合指定名称或类型的服务自动注入到当前类的成员变量中。
     * 这里注入的ParkingSignService用于处理与停车标志相关的业务逻辑。
     */
    @Resource private ParkingSignService parkingSignService;

    /**
     * 新增车位
     *
     * @param parking 待添加的车位对象
     */
    @Override
    public void add(Parking parking) {
        // 设置当前日期为车位创建时间
        parking.setTime(DateUtil.now());
        // 执行数据插入操作
        parkingMapper.insert(parking);
    }

    /**
     * 根据ID删除车位
     *
     * @param id 待删除车位的ID
     */
    @Override
    public void deleteById(Integer id) {
        parkingMapper.deleteById(id);
    }

    /**
     * 批量删除车位
     *
     * @param ids 待删除车位的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            parkingMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新车位
     *
     * @param parking 更新后的车位对象
     */
    @Override
    public void updateById(Parking parking) {
        parking.setTime(DateUtil.now());
        parkingMapper.updateById(parking);
    }

    /**
     * 根据ID查询车位
     *
     * @param id 待查询车位的ID
     * @return 匹配的车位对象，或null（无匹配记录）
     */
    @Override
    public Parking selectById(Integer id) {
        Parking parking = parkingMapper.selectById(id);
        Account currentUser = TokenUtils.getCurrentUser();
        ParkingSign parkingSign =
                parkingSignService.selectByParkingIdAndUserId(id, currentUser.getId());
        if (parkingSign == null) {
            parking.setStatus(ParkingStatusEnum.NOT_APPOINTMENT.getStatus());
        } else {
            parking.setStatus(ParkingStatusEnum.IS_APPOINTMENT.getStatus());
        }
        return parking;
    }

    /**
     * 查询所有车位（可能带条件）
     *
     * @param parking 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）
     * @return 符合条件的车位列表
     */
    @Override
    public List<Parking> selectAll(Parking parking) {
        return parkingMapper.selectAll(parking);
    }

    /**
     * 分页查询车位
     *
     * @param parking 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果对象，包含查询结果列表和分页信息
     */
    @Override
    public PageInfo<Parking> selectPage(Parking parking, Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<Parking> list = parkingMapper.selectAll(parking);
        // 封装分页结果
        return PageInfo.of(list);
    }
}
