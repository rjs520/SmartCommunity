package com.rjs.smartcommunity.entity;

import java.io.Serial;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 管理员实体类，继承自Account类并实现Serializable接口
 *
 * @author rjs
 */
@Getter
@Setter
public class Admin extends Account implements Serializable {

    /** 序列化版本号 */
    @Serial private static final long serialVersionUID = 1L;

    /** 管理员ID */
    private Integer id;

    /** 管理员用户名 */
    private String username;

    /** 管理员密码 */
    private String password;

    /** 管理员姓名 */
    private String name;

    /** 管理员联系电话 */
    private String phone;

    /** 管理员电子邮箱 */
    private String email;

    /** 管理员头像URL或路径 */
    private String avatar;

    /** 管理员角色标识 */
    private String role;
}
