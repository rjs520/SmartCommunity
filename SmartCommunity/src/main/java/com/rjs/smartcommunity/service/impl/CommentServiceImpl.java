package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Comment;
import com.rjs.smartcommunity.mapper.CommentMapper;
import com.rjs.smartcommunity.service.CommentService;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 评论业务逻辑实现类
 *
 * @author rjs
 */
@Service("CommentService")
public class CommentServiceImpl implements CommentService {

    /** 评论数据操作接口 */
    @Resource private CommentMapper commentMapper;

    /**
     * 新增评论
     *
     * @param comment 待添加的评论对象，包含服务的详细信息。
     */
    @Override
    public void add(Comment comment) {
        comment.setTime(DateUtil.now());
        if (comment.getPid() != null) {
            // 表示这是一个回复
            // 查出父级的评论
            Comment commentPid = this.selectById(comment.getPid());
            if (commentPid == null) {
                return;
            }
            if (commentPid.getRootId() != null) {
                // 这不是根节点
                comment.setRootId(commentPid.getRootId());
            } else {
                // 这是根节点
                comment.setRootId(commentPid.getId());
            }
        }
        // 执行数据插入操作
        commentMapper.insert(comment);
    }

    /**
     * 根据ID删除评论
     *
     * @param id 待删除评论的ID
     */
    @Override
    public void deleteById(Integer id) {
        commentMapper.deleteById(id);
    }

    /**
     * 批量删除评论
     *
     * @param ids 待删除评论的ID集合
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        // 遍历ID集合，逐个执行删除操作
        for (Integer id : ids) {
            commentMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新评论
     *
     * @param comment 更新后的评论对象，包含更新后的服务信息。
     */
    @Override
    public void updateById(Comment comment) {
        commentMapper.updateById(comment);
    }

    /**
     * 根据ID查询评论
     *
     * @param id 待查询评论的ID
     * @return 匹配的评论对象，如果不存在则返回null。
     */
    @Override
    public Comment selectById(Integer id) {
        return commentMapper.selectById(id);
    }

    /**
     * 查询所有评论（可能带条件）
     *
     * @param comment 可选条件对象，用于传递查询条件，如标题、创建时间等。
     * @return 符合条件的评论列表。
     */
    @Override
    public List<Comment> selectAll(Comment comment) {
        return commentMapper.selectAll(comment);
    }

    /**
     * 分页查询评论
     *
     * @param comment 可选条件对象，用于传递查询条件（如按标题、创建时间等筛选）。
     * @param pageNum 页码。
     * @param pageSize 每页大小。
     * @return 分页结果对象，包含查询结果列表和分页信息。
     */
    @Override
    public PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize) {
        // 初始化分页插件
        PageHelper.startPage(pageNum, pageSize);
        // 执行查询操作
        List<Comment> list = commentMapper.selectAll(comment);
        // 封装分页结果
        return PageInfo.of(list);
    }

    /**
     * 根据给定的父级评论ID和模块名称，查询评论树。 这个方法首先从数据库中查询所有相关的评论，然后将它们组织成一个树状结构，其中每个评论节点包含它的子评论。
     *
     * @param fid 父级评论的ID。如果想要获取最顶层的评论，则此参数为null。
     * @param module 评论所属的模块名称。
     * @return 返回一个包含根评论的列表。每个根评论都包含了它的所有子评论。
     */
    @Override
    public List<Comment> selectTree(Integer fid, String module) {
        // 从数据库中查询所有符合条件的评论
        List<Comment> commentList = commentMapper.selectTree(fid, module);

        // 筛选出所有父级评论（即根评论），并收集到一个新地列表中
        List<Comment> rootList =
                commentList.stream()
                        .filter(comment -> comment.getPid() == null)
                        .collect(Collectors.toList());

        // 遍历所有根评论，为每个根评论查找并设置其子评论列表
        for (Comment root : rootList) {
            Integer rootId = root.getId();
            // 根据根评论ID筛选出所有子评论，并收集到一个列表中
            List<Comment> children =
                    commentList.stream()
                            .filter(comment -> rootId.equals(comment.getRootId()))
                            .collect(Collectors.toList());
            // 将找到的子评论列表设置到对应的根评论上
            root.setChildren(children);
        }

        return rootList;
    }

    /**
     * 根据给定的参数查询评论数量。
     *
     * @param fid 分类ID，可选参数，用于筛选特定分类下的评论数量。
     * @param module 模块名称，可选参数，用于筛选特定模块下的评论数量。
     * @return 返回评论总数。
     */
    @Override
    public int selectCount(Integer fid, String module) {
        // 调用commentMapper的selectCount方法查询指定条件下的评论数量
        return commentMapper.selectCount(fid, module);
    }

    /**
     * 前台递归删除评论方法 该方法通过传入的父级评论ID，递归删除该父级评论及其所有子评论。
     *
     * @param pid 父级评论的ID，用于删除指定评论及其子评论。
     */
    @Override
    public void deepDelete(Integer pid) {
        // 删除指定ID的评论
        commentMapper.deleteById(pid);

        // 查询指定父级评论下的所有子评论
        List<Comment> children = commentMapper.selectByPid(pid);

        // 如果存在子评论，则对每个子评论递归调用deepDelete进行删除
        if (CollUtil.isNotEmpty(children)) {
            for (Comment comment : children) {
                // 获取子评论的ID，用于递归删除
                pid = comment.getId();
                this.deepDelete(pid);
            }
        }
    }
}
