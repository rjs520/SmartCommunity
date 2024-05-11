package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.ComplaintSuggestion;
import com.rjs.smartcommunity.mapper.ComplaintSuggestionMapper;
import com.rjs.smartcommunity.service.ComplaintSuggestionService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 反馈与建议业务逻辑实现类
 *
 * @author rjs
 */
@Service("ComplaintSuggestionService")
public class ComplaintSuggestionServiceImpl implements ComplaintSuggestionService {

    /** 反馈与建议数据操作接口 */
    @Resource private ComplaintSuggestionMapper complaintSuggestionMapper;

    /**
     * 新增反馈与建议
     *
     * @param complaintSuggestion 待添加的反馈与建议对象，包含服务的详细信息。
     */
    @Override
    public void add(ComplaintSuggestion complaintSuggestion) {
        // 设置当前日期为服务的创建时间
        complaintSuggestion.setDate(DateUtil.today());
        // 执行数据插入操作
        complaintSuggestionMapper.insert(complaintSuggestion);
    }

    /**
     * 根据ID删除反馈与建议
     *
     * @param id 待删除反馈与建议的ID
     */
    @Override
    public void deleteById(Integer id) {
        complaintSuggestionMapper.deleteById(id);
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
            complaintSuggestionMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新反馈与建议
     *
     * @param complaintSuggestion 更新后的反馈与建议对象，包含更新后的服务信息。
     */
    @Override
    public void updateById(ComplaintSuggestion complaintSuggestion) {
        complaintSuggestionMapper.updateById(complaintSuggestion);
    }

    /**
     * 根据ID查询反馈与建议
     *
     * @param id 待查询反馈与建议的ID
     * @return 匹配的反馈与建议对象，如果不存在则返回null。
     */
    @Override
    public ComplaintSuggestion selectById(Integer id) {
        return complaintSuggestionMapper.selectById(id);
    }

    /**
     * 查询所有反馈与建议（可能带条件）
     *
     * @param complaintSuggestion 可选条件对象，用于传递查询条件，如标题、创建时间等。
     * @return 符合条件的反馈与建议列表。
     */
    @Override
    public List<ComplaintSuggestion> selectAll(ComplaintSuggestion complaintSuggestion) {
        return complaintSuggestionMapper.selectAll(complaintSuggestion);
    }

    /**
     * 分页查询反馈与建议
     *
     * @param complaintSuggestion 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）。
     * @param pageNum 页码。
     * @param pageSize 每页大小。
     * @return 分页结果对象，包含查询结果列表和分页信息。
     */
    @Override
    public PageInfo<ComplaintSuggestion> selectPage(
            ComplaintSuggestion complaintSuggestion, Integer pageNum, Integer pageSize) {
        // 初始化分页插件
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<ComplaintSuggestion> list = complaintSuggestionMapper.selectAll(complaintSuggestion);
        // 封装分页结果
        return PageInfo.of(list);
    }
}
