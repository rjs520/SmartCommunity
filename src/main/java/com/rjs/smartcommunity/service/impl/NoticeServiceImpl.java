package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.date.DateUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.entity.Notice;
import com.rjs.smartcommunity.mapper.NoticeMapper;
import com.rjs.smartcommunity.service.NoticeService;
import com.rjs.smartcommunity.utils.TokenUtils;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author rjs
 */
@Service("NoticeService")
public class NoticeServiceImpl implements NoticeService {

    /** 公告数据操作接口注入 */
    @Resource private NoticeMapper noticeMapper;

    /**
     * 新增公告信息
     *
     * @param notice 待添加的公告对象
     */
    @Override
    public void add(Notice notice) {
        // 设置当前日期为公告创建时间
        notice.setTime(DateUtil.today());
        // 获取当前登录用户信息，设置公告创建人为当前用户用户名
        Account currentUser = TokenUtils.getCurrentUser();
        notice.setUser(currentUser.getUsername());
        // 执行数据插入操作
        noticeMapper.insert(notice);
    }

    /**
     * 根据ID删除公告信息
     *
     * @param id 待删除公告的ID
     */
    @Override
    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }

    /**
     * 批量删除公告信息
     *
     * @param ids 待删除公告的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            noticeMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新公告信息
     *
     * @param notice 更新后的公告对象
     */
    @Override
    public void updateById(Notice notice) {
        noticeMapper.updateById(notice);
    }

    /**
     * 根据ID查询公告信息
     *
     * @param id 待查询公告的ID
     * @return 匹配的公告对象，或null（无匹配记录）
     */
    @Override
    public Notice selectById(Integer id) {
        return noticeMapper.selectById(id);
    }

    /**
     * 查询所有公告信息（可能带条件）
     *
     * @param notice 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）
     * @return 符合条件的公告列表
     */
    @Override
    public List<Notice> selectAll(Notice notice) {
        return noticeMapper.selectAll(notice);
    }

    /**
     * 分页查询公告信息
     *
     * @param notice 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return 分页结果对象，包含查询结果列表和分页信息
     */
    @Override
    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        // 设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<Notice> list = noticeMapper.selectAll(notice);
        // 封装分页结果
        return PageInfo.of(list);
    }
}
