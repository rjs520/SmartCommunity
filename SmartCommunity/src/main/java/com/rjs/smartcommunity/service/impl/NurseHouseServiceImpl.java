package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.date.DateUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.NurseHouse;
import com.rjs.smartcommunity.mapper.NurseHouseMapper;
import com.rjs.smartcommunity.service.NurseHouseService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 养老院业务逻辑实现类
 *
 * @author rjs
 */
@Service("NurseHouseService")
public class NurseHouseServiceImpl implements NurseHouseService {

    /** 养老院数据操作接口 */
    @Resource private NurseHouseMapper nurseHouseMapper;

    /**
     * 新增养老院
     *
     * @param nurseHouse 待添加的养老院对象，包含服务的详细信息。
     */
    @Override
    public void add(NurseHouse nurseHouse) {
        // 设置当前日期为服务的创建时间
        nurseHouse.setDate(DateUtil.today());
        // 执行数据插入操作
        nurseHouseMapper.insert(nurseHouse);
    }

    /**
     * 根据ID删除养老院
     *
     * @param id 待删除养老院的ID
     */
    @Override
    public void deleteById(Integer id) {
        nurseHouseMapper.deleteById(id);
    }

    /**
     * 批量删除养老院
     *
     * @param ids 待删除养老院的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        // 遍历ID集合，逐个执行删除操作
        for (Integer id : ids) {
            nurseHouseMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新养老院
     *
     * @param nurseHouse 更新后的养老院对象，包含更新后的服务信息。
     */
    @Override
    public void updateById(NurseHouse nurseHouse) {
        nurseHouseMapper.updateById(nurseHouse);
    }

    /**
     * 根据ID查询养老院
     *
     * @param id 待查询养老院的ID
     * @return 匹配的养老院对象，如果不存在则返回null。
     */
    @Override
    public NurseHouse selectById(Integer id) {
        return nurseHouseMapper.selectById(id);
    }

    /**
     * 查询所有养老院（可能带条件）
     *
     * @param nurseHouse 可选条件对象，用于传递查询条件，如标题、创建时间等。
     * @return 符合条件的养老院列表。
     */
    @Override
    public List<NurseHouse> selectAll(NurseHouse nurseHouse) {
        return nurseHouseMapper.selectAll(nurseHouse);
    }

    /**
     * 分页查询养老院
     *
     * @param nurseHouse 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）。
     * @param pageNum 页码。
     * @param pageSize 每页大小。
     * @return 分页结果对象，包含查询结果列表和分页信息。
     */
    @Override
    public PageInfo<NurseHouse> selectPage(
            NurseHouse nurseHouse, Integer pageNum, Integer pageSize) {
        // 初始化分页插件
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<NurseHouse> list = nurseHouseMapper.selectAll(nurseHouse);
        // 封装分页结果
        return PageInfo.of(list);
    }
}
