package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.enums.RoleEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.entity.Recs;
import com.rjs.smartcommunity.mapper.RecsMapper;
import com.rjs.smartcommunity.service.RecsService;
import com.rjs.smartcommunity.utils.TokenUtils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * 反馈与建议业务逻辑实现类
 *
 * @author rjs
 */
@Service("RecsService")
public class RecsServiceImpl implements RecsService {

    /** 反馈与建议数据操作接口 */
    @Resource private RecsMapper recsMapper;

    /**
     * 新增反馈与建议的函数。 这个方法通过调用recsMapper的insert方法，将传入的反馈与建议对象逐个添加到数据库中。
     *
     * @param recsList 待添加的反馈与建议对象的列表。这个列表包含了需要被添加到数据库的所有反馈与建议的信息。 每个反馈与建议对象都详细描述了服务的反馈信息。
     */
    @Override
    public void add(List<Recs> recsList) {
        // 遍历反馈与建议对象列表，并将每个对象添加到数据库
        for (Recs recs : recsList) {
            // 为每个对象设置当前时间
            recs.setTime(DateUtil.today());
            // 将每个对象添加到数据库
            recsMapper.insert(recs);
        }
    }

    /**
     * 根据ID删除反馈与建议
     *
     * @param id 待删除反馈与建议的ID
     */
    @Override
    public void deleteById(Integer id) {
        recsMapper.deleteById(id);
    }

    /**
     * 批量删除反馈与建议
     *
     * @param ids 待删除反馈与建议的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        // 遍历ID集合，逐个执行删除操作
        for (Integer id : ids) {
            recsMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新反馈与建议
     *
     * @param recs 更新后的反馈与建议对象，包含更新后的服务信息。
     */
    @Override
    public void updateById(Recs recs) {
        recsMapper.updateById(recs);
    }

    /**
     * 根据ID查询反馈与建议
     *
     * @param id 待查询反馈与建议的ID
     * @return 匹配的反馈与建议对象，如果不存在则返回null。
     */
    @Override
    public Recs selectById(Integer id) {
        return recsMapper.selectById(id);
    }

    /**
     * 查询所有反馈与建议（可能带条件）
     *
     * @param recs 可选条件对象，用于传递查询条件，如标题、创建时间等。
     * @return 符合条件的反馈与建议列表。
     */
    @Override
    public List<Recs> selectAll(Recs recs) {
        return recsMapper.selectAll(recs);
    }

    /**
     * 分页查询反馈与建议
     *
     * @param recs 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）。
     * @param pageNum 页码。
     * @param pageSize 每页大小。
     * @return 分页结果对象，包含查询结果列表和分页信息。
     */
    @Override
    public PageInfo<Recs> selectPage(Recs recs, Integer pageNum, Integer pageSize) {
        // 初始化分页插件
        PageHelper.startPage(pageNum, pageSize);
        Account currentUser = TokenUtils.getCurrentUser();
        if (currentUser.getRole().equals(RoleEnum.USER.name())) {
            // 普通用户只查询自己的数据
            recs.setUserId(currentUser.getId());
        }
        // 执行查询操作
        List<Recs> list = recsMapper.selectAll(recs);
        // 封装分页结果
        return PageInfo.of(list);
    }

    /**
     * 查询并统计每个反馈与建议人员处理的预约记录数量。
     *
     * <p>该方法首先从数据库中查询所有的预约记录，然后筛选出状态为"已回复"或"待回复"的记录。 接着，通过反馈与建议人员姓名分组，并统计每个反馈与建议人员对应的记录数量。
     * 最后，将结果以List<Dict>的形式返回，其中Dict包含反馈与建议人员姓名和对应的记录数量。
     *
     * @return List<Dict> 包含每个反馈与建议人员及其处理的预约记录数量的列表。
     */
    @Override
    public List<Dict> selectCount() {
        // 从数据库查询所有预约记录
        List<Recs> recsList = recsMapper.selectAll(null);

        // 筛选出状态为"已回复"或"待回复"的预约记录
        recsList =
                recsList.stream()
                        .filter(
                                recs ->
                                        "已回复".equals(recs.getStatus())
                                                || "待回复".equals(recs.getStatus()))
                        .toList();

        // 提取并去重所有反馈与建议人员的姓名
        Set<String> set = recsList.stream().map(Recs::getCsName).collect(Collectors.toSet());

        // 初始化结果列表
        List<Dict> list = CollUtil.newArrayList();

        // 统计每个反馈与建议人员的预约记录数量，并添加到结果列表中
        for (String name : set) {
            long count = recsList.stream().filter(recs -> recs.getCsName().equals(name)).count();

            Dict dict = Dict.create().set("name", name).set("value", count);
            list.add(dict);
        }

        return list;
    }

    /**
     * 根据反馈与建议ID和用户ID选择推荐记录。
     *
     * @param csId 反馈与建议ID，用于筛选与特定反馈与建议相关的推荐记录。
     * @param userId 用户ID，用于筛选与特定用户相关的推荐记录。
     * @return 返回一个推荐记录列表，这些记录与给定的反馈与建议ID和用户ID匹配。
     */
    @Override
    public List<Recs> selectByCsIdAndUserId(Integer csId, Integer userId) {
        // 调用recsMapper中的方法，根据提供的csId和userId查询相应的推荐记录
        return recsMapper.selectByCsIdAndUserId(csId, userId);
    }
}
