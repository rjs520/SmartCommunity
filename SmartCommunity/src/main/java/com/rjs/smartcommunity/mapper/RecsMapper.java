package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.Recs;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作recs相关数据接口
 *
 * @author rjs
 */
@Mapper
public interface RecsMapper {

    /**
     * 新增recs记录
     *
     * @param recs 需要新增的反馈与建议实体
     * @return 返回影响的行数
     */
    int insert(Recs recs);

    /**
     * 根据ID删除反馈与建议记录
     *
     * @param id 需要删除的反馈与建议的ID
     * @return 返回影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID修改反馈与建议记录
     *
     * @param recs 需要修改的反馈与建议实体，其中包含了修改后的数据
     * @return 返回影响的行数
     */
    int updateById(Recs recs);

    /**
     * 根据ID查询反馈与建议记录
     *
     * @param id 需要查询的反馈与建议的ID
     * @return 返回查询到的反馈与建议实体，如果不存在则返回null
     */
    Recs selectById(Integer id);

    /**
     * 查询所有反馈与建议记录
     *
     * @param recs 可选参数，用于可能的查询条件构建。如果不提供任何条件，则查询全部
     * @return 返回反馈与建议记录的列表
     */
    List<Recs> selectAll(Recs recs);

    /**
     * 查询特定客服人员（csId）和用户（userId）之间的所有反馈与建议记录。 这个方法通过传入的客服人员ID和用户ID来筛选反馈与建议记录。
     *
     * @param csId 客服人员的ID，用于查询特定客服的反馈与建议记录。这是一个必传参数。
     * @param userId 用户的ID，用于查询特定用户向客服提供的反馈与建议记录。这是一个必传参数。
     * @return 返回一个反馈与建议记录的列表，这些记录都是特定客服人员和用户之间的交流。
     */
    List<Recs> selectByCsIdAndUserId(@Param("csId") Integer csId, @Param("userId") Integer userId);
}
