package com.rjs.smartcommunity.service;

import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.entity.Admin;
import com.rjs.smartcommunity.exception.CustomException;

import java.util.List;

/**
 * 管理员业务处理服务类
 *
 * @author rjs
 */
public interface AdminService {

    /**
     * 添加管理员
     *
     * @param admin 待添加的管理员对象，包含管理员的所有必要属性（如用户名、密码、角色等）
     */
    void add(Admin admin);

    /**
     * 通过ID删除管理员
     *
     * @param id 需要删除的管理员的唯一标识符（ID）
     */
    void deleteById(Integer id);

    /**
     * 批量删除管理员
     *
     * @param ids 一个包含多个待删除管理员ID的列表
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 更新管理员信息
     *
     * @param admin 包含更新后管理员属性的对象，其ID字段用于标识待更新的管理员记录
     */
    void updateById(Admin admin);

    /**
     * 根据ID查询管理员
     *
     * @param id 要查询的管理员的唯一标识符（ID）
     * @return 返回与指定ID匹配的管理员对象，若未找到对应记录，则返回null
     */
    Admin selectById(Integer id);

    /**
     * 查询所有管理员（可带条件过滤）
     *
     * @param admin 可选的查询条件对象，用于设定筛选条件（如用户名、状态等）。如果传入null或空对象，表示查询全部管理员。
     * @return 返回满足条件的所有管理员对象组成的列表
     */
    List<Admin> selectAll(Admin admin);

    /**
     * 分页查询管理员
     *
     * @param admin 可选的查询条件对象，用于设定筛选条件（如用户名、状态等）。如果传入null或空对象，表示仅按分页参数进行查询。
     * @param pageNum 当前请求的页码（从1开始）
     * @param pageSize 每页显示的记录数
     * @return 返回封装了查询结果的分页对象（PageInfo），包含了当前页的管理员列表以及总记录数、总页数等信息
     */
    PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize);

    /**
     * 管理员登录
     *
     * @param account 包含用户名和密码的账号信息
     * @return 返回登录成功的账号对象，通常包含用户权限、角色等信息。若登录失败（如用户名不存在、密码错误等），则抛出异常。
     * @throws CustomException 当登录失败时抛出的自定义异常，可能包含具体的错误原因
     */
    Account login(Account account);

    /**
     * 注册新账号
     *
     * @param account 包含新账号所需的所有信息（如用户名、密码、邮箱等）
     * @throws CustomException 若注册过程中出现异常情况（如用户名已存在、邮箱格式不正确等），则抛出自定义异常
     */
    void register(Account account);

    /**
     * 修改管理员密码
     *
     * @param account 包含当前用户名、旧密码、新密码的账号信息
     * @throws CustomException 用户不存在或旧密码错误时抛出自定义异常
     */
    void updatePassword(Account account);
}
