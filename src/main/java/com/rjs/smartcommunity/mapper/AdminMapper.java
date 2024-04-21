package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.Admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 管理员数据操作接口，使用MyBatis的Mapper注解
 *
 * @author rjs
 */
@Mapper
public interface AdminMapper {

    /**
     * 新增管理员信息
     *
     * @param admin 待插入的管理员对象（Admin实体类）
     * @return 受影响的行数
     */
    int insert(Admin admin);

    /**
     * 根据ID删除管理员信息
     *
     * @param id 管理员ID
     * @return 受影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID更新管理员信息
     *
     * @param admin 更新后的管理员对象（Admin实体类）
     * @return 受影响的行数
     */
    int updateById(Admin admin);

    /**
     * 根据ID查询管理员信息
     *
     * @param id 管理员ID
     * @return 匹配的管理员对象（Admin实体类），或null（无匹配记录）
     */
    Admin selectById(Integer id);

    /**
     * 查询所有管理员信息（可能带条件）
     *
     * @param admin 可选条件对象（Admin实体类），用于传递查询条件（如按用户名、角色等筛选）
     * @return 符合条件的管理员列表（List<Admin>）
     */
    List<Admin> selectAll(Admin admin);

    /**
     * 根据用户名查询管理员信息
     *
     * @param username 管理员用户名
     * @return 匹配的管理员对象（Admin实体类），或null（无匹配记录）
     */
    @Select("select * from admin where username = #{username}")
    Admin selectByUsername(String username);
}
