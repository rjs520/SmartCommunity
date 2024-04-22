package com.rjs.smartcommunity.service;

import cn.hutool.core.util.ObjectUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Constants;
import com.rjs.smartcommunity.common.enums.ResultCodeEnum;
import com.rjs.smartcommunity.common.enums.RoleEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.entity.Admin;
import com.rjs.smartcommunity.exception.CustomException;
import com.rjs.smartcommunity.mapper.AdminMapper;
import com.rjs.smartcommunity.utils.TokenUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * 管理员业务处理服务类
 *
 * @author rjs
 */
@Service
public class AdminService {

    /** 管理员数据访问层（DAO）对象，用于操作数据库中的管理员数据 */
    @Resource private AdminMapper adminMapper;

    /**
     * 添加新管理员
     *
     * @param admin 待添加的管理员对象
     */
    public void add(Admin admin) {
        // 检查数据库中是否存在相同用户名的管理员
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if (ObjectUtil.isNotNull(dbAdmin)) {
            // 如果存在，抛出用户已存在的自定义异常
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }

        // 如果传入的管理员对象密码为空，设置默认密码
        if (ObjectUtil.isEmpty(admin.getPassword())) {
            admin.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }

        // 如果传入的管理员对象姓名为空，将其用户名作为姓名
        if (ObjectUtil.isEmpty(admin.getName())) {
            admin.setName(admin.getUsername());
        }

        // 设置管理员角色为"管理员"
        admin.setRole(RoleEnum.ADMIN.name());

        // 将新管理员信息插入数据库
        adminMapper.insert(admin);
    }

    /**
     * 根据ID删除管理员
     *
     * @param id 要删除的管理员ID
     */
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    /**
     * 批量删除管理员
     *
     * @param ids 要删除的管理员ID列表
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            adminMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新管理员信息
     *
     * @param admin 包含更新后信息的管理员对象
     */
    public void updateById(Admin admin) {
        adminMapper.updateById(admin);
    }

    /**
     * 根据ID查询管理员信息
     *
     * @param id 要查询的管理员ID
     * @return 对应ID的管理员对象
     */
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    /**
     * 查询所有管理员信息
     *
     * @param admin 可包含筛选条件的管理员对象（如用户名、角色等）
     * @return 符合条件的所有管理员对象列表
     */
    public List<Admin> selectAll(Admin admin) {
        return adminMapper.selectAll(admin);
    }

    /**
     * 分页查询管理员信息
     *
     * @param admin 可包含筛选条件的管理员对象（如用户名、角色等）
     * @param pageNum 当前页码
     * @param pageSize 每页显示数量
     * @return 分页后的管理员信息包装对象，包含查询结果列表及分页信息
     */
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        // 使用PageHelper插件进行分页处理
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        // 封装分页结果为PageInfo对象返回
        return PageInfo.of(list);
    }

    /**
     * 管理员登录
     *
     * @param account 登录账号信息（用户名、密码）
     * @return 登录成功后的账号信息（包含生成的token）
     * @throws CustomException 用户不存在或密码错误时抛出自定义异常
     */
    public Account login(Account account) {
        // 根据用户名查询数据库中是否存在该管理员
        Account dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            // 如果不存在，抛出用户不存在的自定义异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // 比较输入密码与数据库中存储的密码是否一致
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            // 如果不一致，抛出密码错误的自定义异常
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        // 生成token，使用管理员ID和角色名作为数据源，使用管理员密码作为加密密钥
        String tokenData = dbAdmin.getId() + "-" + RoleEnum.ADMIN.name();
        String token = TokenUtils.createToken(tokenData, dbAdmin.getPassword());

        // 将生成的token设置到管理员对象中，并返回
        dbAdmin.setToken(token);
        return dbAdmin;
    }

    /**
     * 注册管理员
     *
     * @param account 待注册的账号信息（用户名、密码、姓名等）
     */
    public void register(Account account) {
        // 创建一个新的Admin对象
        Admin admin = new Admin();

        // 将Account对象中的属性复制到Admin对象中
        BeanUtils.copyProperties(account, admin);

        // 调用add方法将新创建的管理员添加到数据库
        add(admin);
    }

    /**
     * 修改管理员密码
     *
     * @param account 包含当前用户名、旧密码、新密码的账号信息
     * @throws CustomException 用户不存在或旧密码错误时抛出自定义异常
     */
    public void updatePassword(Account account) {
        // 根据用户名查询数据库中是否存在该管理员
        Admin dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            // 如果不存在，抛出用户不存在的自定义异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // 比较输入的旧密码与数据库中存储的密码是否一致
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            // 如果不一致，抛出旧密码错误的自定义异常
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }

        // 更新管理员的密码
        dbAdmin.setPassword(account.getNewPassword());

        // 调用updateById方法更新数据库中的管理员信息
        adminMapper.updateById(dbAdmin);
    }
}
