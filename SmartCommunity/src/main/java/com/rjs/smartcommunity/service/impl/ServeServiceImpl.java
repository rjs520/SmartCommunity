package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.date.DateUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Serve;
import com.rjs.smartcommunity.mapper.ServeMapper;
import com.rjs.smartcommunity.service.ServeService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 社区服务业务逻辑实现类
 *
 * @author rjs
 */
@Service("ServeService")
public class ServeServiceImpl implements ServeService {

    /** 社区服务数据操作接口 */
    @Resource private ServeMapper serveMapper;

    /**
     * 新增社区服务
     *
     * @param serve 待添加的社区服务对象，包含服务的详细信息。
     */
    @Override
    public void add(Serve serve) {
        // 设置当前日期为服务的创建时间
        serve.setDate(DateUtil.today());
        // 执行数据插入操作
        serveMapper.insert(serve);
    }

    /**
     * 根据ID删除社区服务
     *
     * @param id 待删除社区服务的ID
     */
    @Override
    public void deleteById(Integer id) {
        serveMapper.deleteById(id);
    }

    /**
     * 批量删除社区服务
     *
     * @param ids 待删除社区服务的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        // 遍历ID集合，逐个执行删除操作
        for (Integer id : ids) {
            serveMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新社区服务
     *
     * @param serve 更新后的社区服务对象，包含更新后的服务信息。
     */
    @Override
    public void updateById(Serve serve) {
        serveMapper.updateById(serve);
    }

    /**
     * 根据ID查询社区服务
     *
     * @param id 待查询社区服务的ID
     * @return 匹配的社区服务对象，如果不存在则返回null。
     */
    @Override
    public Serve selectById(Integer id) {
        return serveMapper.selectById(id);
    }

    /**
     * 查询所有社区服务（可能带条件）
     *
     * @param serve 可选条件对象，用于传递查询条件，如标题、创建时间等。
     * @return 符合条件的社区服务列表。
     */
    @Override
    public List<Serve> selectAll(Serve serve) {
        return serveMapper.selectAll(serve);
    }

    /**
     * 分页查询社区服务
     *
     * @param serve 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）。
     * @param pageNum 页码。
     * @param pageSize 每页大小。
     * @return 分页结果对象，包含查询结果列表和分页信息。
     */
    @Override
    public PageInfo<Serve> selectPage(Serve serve, Integer pageNum, Integer pageSize) {
        // 初始化分页插件
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<Serve> list = serveMapper.selectAll(serve);
        // 封装分页结果
        return PageInfo.of(list);
    }
}
