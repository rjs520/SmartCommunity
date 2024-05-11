package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.ComplaintSuggestion;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ComplaintSuggestion实体的数据映射接口。
 *
 * @author rjs
 */
@Mapper
public interface ComplaintSuggestionMapper {
    /**
     * 新增ComplaintSuggestion记录。
     *
     * @param complaintSuggestion ComplaintSuggestion实体对象，包含新增记录的信息。
     * @return 返回影响的行数，通常为1（若插入成功）。
     */
    int insert(ComplaintSuggestion complaintSuggestion);

    /**
     * 根据ID删除ComplaintSuggestion记录。
     *
     * @param id 要删除的记录的ID。
     * @return 返回影响的行数，通常为1（若删除成功）。
     */
    int deleteById(Integer id);

    /**
     * 根据ID修改ComplaintSuggestion记录。
     *
     * @param complaintSuggestion 包含修改后信息的ComplaintSuggestion实体对象。
     * @return 返回影响的行数，通常为1（若修改成功）。
     */
    int updateById(ComplaintSuggestion complaintSuggestion);

    /**
     * 根据ID查询ComplaintSuggestion记录。
     *
     * @param id 要查询的记录的ID。
     * @return 返回匹配的ComplaintSuggestion实体对象，如果不存在则返回null。
     */
    ComplaintSuggestion selectById(Integer id);

    /**
     * 查询所有ComplaintSuggestion记录。
     *
     * @param complaintSuggestion 可选的ComplaintSuggestion实体对象，可用于过滤查询结果（目前方法实现可能不支持过滤）。
     * @return 返回包含所有ComplaintSuggestion记录的列表。
     */
    List<ComplaintSuggestion> selectAll(ComplaintSuggestion complaintSuggestion);
}
