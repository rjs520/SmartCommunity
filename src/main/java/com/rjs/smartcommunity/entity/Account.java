package com.rjs.smartcommunity.entity;

import lombok.Getter;
import lombok.Setter;

/** 角色用户父类实体
 * @author rjs*/
@Setter
@Getter
public class Account {

    /** 账号ID */
    private Integer id;

    /** 用户名 */
    private String username;

    /** 名称（如真实姓名） */
    private String name;

    /** 密码 */
    private String password;

    /** 角色标识（如"ADMIN", "USER"等） */
    private String role;

    /** 新密码（用于密码修改操作） */
    private String newPassword;

    /** 头像URL或路径 */
    private String avatar;

    /** 认证Token */
    private String token;
}
