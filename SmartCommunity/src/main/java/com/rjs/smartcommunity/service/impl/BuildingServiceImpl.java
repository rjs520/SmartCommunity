package com.rjs.smartcommunity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Building;
import com.rjs.smartcommunity.mapper.BuildingMapper;
import com.rjs.smartcommunity.service.BuildingService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 楼宇业务逻辑实现类
 *
 * @author rjs
 */
@Service("BuildingService")
public class BuildingServiceImpl implements BuildingService {

    /** 楼宇数据操作接口 */
    @Resource private BuildingMapper buildingMapper;

    /**
     * 新增楼宇
     *
     * @param building 待添加的楼宇对象，包含服务的详细信息。
     */
    @Override
    public void add(Building building) {
        // 执行数据插入操作
        buildingMapper.insert(building);
    }

    /**
     * 根据ID删除楼宇
     *
     * @param id 待删除楼宇的ID
     */
    @Override
    public void deleteById(Integer id) {
        buildingMapper.deleteById(id);
    }

    /**
     * 批量删除楼宇
     *
     * @param ids 待删除楼宇的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        // 遍历ID集合，逐个执行删除操作
        for (Integer id : ids) {
            buildingMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新楼宇
     *
     * @param building 更新后的楼宇对象，包含更新后的服务信息。
     */
    @Override
    public void updateById(Building building) {
        buildingMapper.updateById(building);
    }

    /**
     * 根据ID查询楼宇
     *
     * @param id 待查询楼宇的ID
     * @return 匹配的楼宇对象，如果不存在则返回null。
     */
    @Override
    public Building selectById(Integer id) {
        return buildingMapper.selectById(id);
    }

    /**
     * 查询所有楼宇（可能带条件）
     *
     * @param building 可选条件对象，用于传递查询条件，如标题、创建时间等。
     * @return 符合条件的楼宇列表。
     */
    @Override
    public List<Building> selectAll(Building building) {
        return buildingMapper.selectAll(building);
    }

    /**
     * 分页查询楼宇
     *
     * @param building 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）。
     * @param pageNum 页码。
     * @param pageSize 每页大小。
     * @return 分页结果对象，包含查询结果列表和分页信息。
     */
    @Override
    public PageInfo<Building> selectPage(Building building, Integer pageNum, Integer pageSize) {
        // 初始化分页插件
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<Building> list = buildingMapper.selectAll(building);
        // 封装分页结果
        return PageInfo.of(list);
    }
}
