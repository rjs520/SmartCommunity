package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.Comment;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 操作comment相关数据接口
 *
 * @author rjs
 */
@Mapper
public interface CommentMapper {

    /**
     * 新增评论
     *
     * @param comment 评论实体对象
     * @return 返回插入的行数
     */
    int insert(Comment comment);

    /**
     * 根据ID删除评论
     *
     * @param id 评论的ID
     * @return 返回删除的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID修改评论
     *
     * @param comment 评论实体对象，包含要修改的内容
     * @return 返回更新的行数
     */
    int updateById(Comment comment);

    /**
     * 根据ID查询评论
     *
     * @param id 评论的ID
     * @return 返回查询到的评论实体对象，如果没有找到返回null
     */
    Comment selectById(Integer id);

    /**
     * 查询所有评论
     *
     * @param comment 可选的评论实体，用于可能的过滤条件（本接口未实现过滤功能，仅提供扩展可能性）
     * @return 返回评论实体列表，如果没有评论则返回空列表
     */
    List<Comment> selectAll(Comment comment);

    List<Comment> selectTree(@Param("fid") Integer fid, @Param("module") String module);
}
