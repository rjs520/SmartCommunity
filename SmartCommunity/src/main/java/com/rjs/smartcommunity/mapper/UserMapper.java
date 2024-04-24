package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户数据操作接口，使用MyBatis的Mapper注解
 *
 * @author rjs
 */
@Mapper
public interface UserMapper {

    /**
     * 新增用户信息
     *
     * @param user 待插入的用户对象（User实体类）
     * @return 受影响的行数
     */
    int insert(User user);

    /**
     * 根据ID删除用户信息
     *
     * @param id 用户ID
     * @return 受影响的行数
     */
    int deleteById(Integer id);

    /**
     * 根据ID更新用户信息
     *
     * @param user 更新后的用户对象（User实体类）
     * @return 受影响的行数
     */
    int updateById(User user);

    /**
     * 根据ID查询用户信息
     *
     * @param id 用户ID
     * @return 匹配的用户对象（User实体类），或null（无匹配记录）
     */
    User selectById(Integer id);

    /**
     * 查询所有用户信息（可能带条件）
     *
     * @param user 可选条件对象（User实体类），用于传递查询条件（如按用户名、角色等筛选）
     * @return 符合条件的用户列表（List<User>）
     */
    List<User> selectAll(User user);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户用户名
     * @return 匹配的用户对象（User实体类），或null（无匹配记录）
     */
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);
}
