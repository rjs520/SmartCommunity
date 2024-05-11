package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;

import cn.hutool.core.lang.Dict;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.enums.RoleEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.entity.Reserve;
import com.rjs.smartcommunity.mapper.ReserveMapper;
import com.rjs.smartcommunity.service.ReserveService;

import com.rjs.smartcommunity.utils.TokenUtils;
import java.util.Set;
import java.util.stream.Collectors;
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
        reserve.setTime(DateUtil.now());
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
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            reserve.setUserId(currentUser.getId());
        }
        // 初始化分页插件
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<Reserve> list = reserveMapper.selectAll(reserve);
        // 封装分页结果
        return PageInfo.of(list);
    }

    /**
     * 查询并统计所有服务的预约数量。 该方法首先从数据库中查询所有的预约记录，然后过滤出状态为"审核通过"或"待审核"的记录。 接着，通过服务名称对这些记录进行分组，并统计每个服务的预约数量。
     * 最后，将每个服务的名称和对应的预约数量封装为字典类型列表并返回。
     *
     * @return 返回包含每个服务名称及其预约数量的字典类型列表。
     */
    @Override
    public List<Dict> selectCount() {

        // 从数据库中查询所有的预约记录
        List<Reserve> reserveList = reserveMapper.selectAll(null);

        // 过滤出状态为"审核通过"或"待审核"的预约记录
        reserveList =
                reserveList.stream()
                        .filter(
                                reserve ->
                                        "审核通过".equals(reserve.getStatus())
                                                || "待审核".equals(reserve.getStatus()))
                        .toList();

        // 通过服务名称对预约记录进行去重
        Set<String> set =
                reserveList.stream().map(Reserve::getServeName).collect(Collectors.toSet());

        // 初始化结果列表
        List<Dict> list = CollUtil.newArrayList();
        // 遍历服务名称集合，统计每个服务的预约数量，并添加到结果列表中
        for (String name : set) {
            // 统计服务的预约数量
            long count =
                    reserveList.stream()
                            .filter(reserve -> reserve.getServeName().equals(name))
                            .count();

            // 将服务名称和预约数量封装为字典类型
            Dict dict = Dict.create().set("name", name).set("value", count);
            list.add(dict);
        }

        return list;
    }
}
