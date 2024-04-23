package com.rjs.smartcommunity.service.impl;

import cn.hutool.core.util.ObjectUtil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rjs.smartcommunity.common.Constants;
import com.rjs.smartcommunity.common.enums.ResultCodeEnum;
import com.rjs.smartcommunity.common.enums.RoleEnum;
import com.rjs.smartcommunity.entity.Account;
import com.rjs.smartcommunity.entity.User;
import com.rjs.smartcommunity.exception.CustomException;
import com.rjs.smartcommunity.mapper.UserMapper;
import com.rjs.smartcommunity.service.UserService;
import com.rjs.smartcommunity.utils.Md5Util;
import com.rjs.smartcommunity.utils.TokenUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author rjs
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource private UserMapper userMapper;

    /**
     * 添加新用户
     *
     * @param user 待添加的用户对象
     */
    @Override
    public void add(User user) {
        // 检查数据库中是否存在相同用户名的用户
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (ObjectUtil.isNotNull(dbUser)) {
            // 如果存在，抛出用户已存在的自定义异常
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }

        // 如果传入的用户对象密码为空，设置默认密码
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }

        // 如果传入的用户对象姓名为空，将其用户名作为姓名
        if (ObjectUtil.isEmpty(user.getName())) {
            user.setName(user.getUsername());
        }

        // 设置用户角色为"用户"
        user.setRole(RoleEnum.USER.name());

        // 将新用户信息插入数据库
        userMapper.insert(user);
    }

    /**
     * 根据ID删除用户
     *
     * @param id 要删除的用户ID
     */
    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 批量删除用户
     *
     * @param ids 要删除的用户ID列表
     */
    @Override
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            userMapper.deleteById(id);
        }
    }

    /**
     * 根据ID更新用户信息
     *
     * @param user 包含更新后信息的用户对象
     */
    @Override
    public void updateById(User user) {
        userMapper.updateById(user);
    }

    /**
     * 根据ID查询用户信息
     *
     * @param id 要查询的用户ID
     * @return 对应ID的用户对象
     */
    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 查询所有用户信息
     *
     * @param user 可包含筛选条件的用户对象（如用户名、角色等）
     * @return 符合条件的所有用户对象列表
     */
    @Override
    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    /**
     * 分页查询用户信息
     *
     * @param user 可包含筛选条件的用户对象（如用户名、角色等）
     * @param pageNum 当前页码
     * @param pageSize 每页显示数量
     * @return 分页后的用户信息包装对象，包含查询结果列表及分页信息
     */
    @Override
    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        // 使用PageHelper插件进行分页处理
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        // 封装分页结果为PageInfo对象返回
        return PageInfo.of(list);
    }

    /**
     * 用户登录
     *
     * @param account 登录账号信息（用户名、密码）
     * @return 登录成功后的账号信息（包含生成的token）
     * @throws CustomException 用户不存在或密码错误时抛出自定义异常
     */
    @Override
    public Account login(Account account) {
        // 根据用户名查询数据库中是否存在该用户
        Account dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            // 如果不存在，抛出用户不存在的自定义异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }
        // 对用户输入的密码进行MD5加密
        String md5Password = Md5Util.getMd5String(account.getPassword());

        // 比较加密后的密码与数据库中存储的密码是否一致
        if (!md5Password.equals(dbUser.getPassword())) {
            // 如果不一致，抛出密码错误的自定义异常
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        // 比较输入密码与数据库中存储的密码是否一致
        // if (!account.getPassword().equals(dbUser.getPassword())) {
        //     // 如果不一致，抛出密码错误的自定义异常
        //     throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        // }

        // 生成token，使用用户ID和角色名作为数据源，使用用户密码作为加密密钥
        String tokenData = dbUser.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokenData, dbUser.getPassword());

        // 将生成的token设置到用户对象中，并返回
        dbUser.setToken(token);
        return dbUser;
    }

    /**
     * 注册用户
     *
     * @param account 待注册的账号信息（用户名、密码、姓名等）
     */
    @Override
    public void register(Account account) {
        // 创建一个新的User对象
        User user = new User();

        // 将Account对象中的属性复制到User对象中
        BeanUtils.copyProperties(account, user);

        // 对密码进行MD5加密
        user.setPassword(Md5Util.getMd5String(user.getPassword()));

        // 调用add方法将新创建的用户添加到数据库
        add(user);
    }

    /**
     * 修改用户密码
     *
     * @param account 包含当前用户名、旧密码、新密码的账号信息
     * @throws CustomException 用户不存在或旧密码错误时抛出自定义异常
     */
    @Override
    public void updatePassword(Account account) {
        // 根据用户名查询数据库中是否存在该用户
        User dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            // 如果不存在，抛出用户不存在的自定义异常
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // 对用户输入的旧密码进行MD5加密
        String md5OldPassword = Md5Util.getMd5String(account.getPassword());

        // 比较加密后的旧密码与数据库中存储的密码是否一致
        if (!md5OldPassword.equals(dbUser.getPassword())) {
            // 如果不一致，抛出旧密码错误的自定义异常
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }

        // 对用户输入的新密码进行MD5加密
        String md5NewPassword = Md5Util.getMd5String(account.getNewPassword());

        // 更新用户的密码
        dbUser.setPassword(md5NewPassword);

        // 调用updateById方法更新数据库中的用户信息
        userMapper.updateById(dbUser);
    }
}
