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

    /**
     * 从数据库中选择评论树。
     *
     * @param fid 主键ID，用于指定评论的父ID。如果评论是回复另一个评论，则该ID为回复评论的ID；如果评论是顶级评论，则该ID为null或特定的顶级评论ID。
     * @param module 模块名称，用于指定评论所属的模块，如文章、视频等。
     * @return 返回一个评论列表，这些评论属于指定的父ID和模块。列表中的每个评论对象可能包含一个子评论列表，形成一棵评论树。
     */
    List<Comment> selectTree(@Param("fid") Integer fid, @Param("module") String module);

    /**
     * 查询指定条件下的记录数量。
     *
     * @param fid 分类ID，用于筛选特定分类的记录。可以为null，表示不根据分类进行筛选。
     * @param module 模块名称，用于筛选特定模块的记录。不能为空字符串，表示不根据模块进行筛选。
     * @return 返回符合条件的记录数量。
     */
    int selectCount(@Param("fid") Integer fid, @Param("module") String module);

    /**
     * 根据帖子ID（pid）选择相关的评论列表。
     *
     * @param pid 帖子的唯一标识符，用于查询该帖子下的所有评论。
     * @return 返回一个评论列表，这些评论都属于指定的帖子。
     */
    List<Comment> selectByPid(Integer pid);
}
