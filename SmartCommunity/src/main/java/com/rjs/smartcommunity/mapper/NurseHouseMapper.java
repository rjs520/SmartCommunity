package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.NurseHouse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * 养老院映射接口
 *
 * @author rjs
 */
@Mapper
public interface NurseHouseMapper {

    /**
     * 新增养老院映射信息
     *
     * @param nurseHouse 养老院实体对象
     * @return 返回影响的行数
     */
    int insert(NurseHouse nurseHouse);

    /**
     * 根据ID删除养老院映射信息
     *
     * @param id 映射信息的ID
     * @return 返回影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID修改养老院映射信息
     *
     * @param nurseHouse 养老院实体对象
     * @return 返回影响的行数
     */
    int updateById(NurseHouse nurseHouse);

    /**
     * 根据ID查询养老院映射信息
     *
     * @param id 映射信息的ID
     * @return 返回查询到的养老院实体对象
     */
    NurseHouse selectById(Integer id);

    /**
     * 查询所有养老院映射信息
     *
     * @param nurseHouse 养老院实体对象，可用于过滤查询条件
     * @return 返回养老院映射信息的列表
     */
    List<NurseHouse> selectAll(NurseHouse nurseHouse);
}
