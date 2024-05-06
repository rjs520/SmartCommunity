package com.rjs.smartcommunity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Reserve;
import com.rjs.smartcommunity.mapper.ReserveMapper;
import com.rjs.smartcommunity.service.ReserveService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 服务预约业务逻辑实现类
 *
 * @author rjs
 */
@Service("ReserveService")
public class ReserveServiceImpl implements ReserveService {

    /** 服务预约数据操作接口 */
    @Resource private ReserveMapper reserveMapper;

    /**
     * 新增服务预约
     *
     * @param reserve 待添加的服务预约对象，包含服务的详细信息。
     */
    @Override
    public void add(Reserve reserve) {
        // 执行数据插入操作
        reserveMapper.insert(reserve);
    }

    /**
     * 根据ID删除服务预约
     *
     * @param id 待删除服务预约的ID
     */
    @Override
    public void deleteById(Integer id) {
        reserveMapper.deleteById(id);
    }

    /**
     * 批量删除服务预约
     *
     * @param ids 待删除服务预约的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        // 遍历ID集合，逐个执行删除操作
        for (Integer id : ids) {
            reserveMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新服务预约
     *
     * @param reserve 更新后的服务预约对象，包含更新后的服务信息。
     */
    @Override
    public void updateById(Reserve reserve) {
        reserveMapper.updateById(reserve);
    }

    /**
     * 根据ID查询服务预约
     *
     * @param id 待查询服务预约的ID
     * @return 匹配的服务预约对象，如果不存在则返回null。
     */
    @Override
    public Reserve selectById(Integer id) {
        return reserveMapper.selectById(id);
    }

    /**
     * 查询所有服务预约（可能带条件）
     *
     * @param reserve 可选条件对象，用于传递查询条件，如标题、创建时间等。
     * @return 符合条件的服务预约列表。
     */
    @Override
    public List<Reserve> selectAll(Reserve reserve) {
        return reserveMapper.selectAll(reserve);
    }

    /**
     * 分页查询服务预约
     *
     * @param reserve 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）。
     * @param pageNum 页码。
     * @param pageSize 每页大小。
     * @return 分页结果对象，包含查询结果列表和分页信息。
     */
    @Override
    public PageInfo<Reserve> selectPage(Reserve reserve, Integer pageNum, Integer pageSize) {
        // 初始化分页插件
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<Reserve> list = reserveMapper.selectAll(reserve);
        // 封装分页结果
        return PageInfo.of(list);
    }
}
