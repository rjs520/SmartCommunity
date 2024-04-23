package com.rjs.smartcommunity.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户实体类，继承自Account类并实现Serializable接口
 *
 * @author rjs
 */
@Getter
@Setter
public class User extends Account implements Serializable {

    /** 序列化版本号 */
    @Serial private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Integer id;

    /** 用户用户名 */
    private String username;

    /** 用户密码 */
    private String password;

    /** 用户姓名 */
    private String name;

    /** 用户联系电话 */
    private String phone;

    /** 用户电子邮箱 */
    private String email;

    /** 用户头像URL或路径 */
    private String avatar;

    /** 用户角色标识 */
    private String role;
}
