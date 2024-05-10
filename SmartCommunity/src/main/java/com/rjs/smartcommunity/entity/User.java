package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户实体类，继承自Account类并实现Serializable接口
 *
 * @author rjs
 */
@Schema(description = "用户实体类")
@Getter
@Setter
public class User extends Account implements Serializable {

    /** 序列化版本号 */
    @Serial private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Schema(description = "用户ID", example = "1")
    private Integer id;

    /** 用户用户名 */
    @Schema(description = "用户名", example = "zhangsan")
    private String username;

    /** 用户密码 */
    @Schema(description = "密码", example = "123456")
    private String password;

    /** 用户姓名 */
    @Schema(description = "姓名", example = "张三")
    private String name;

    /** 用户联系电话 */
    @Schema(description = "联系电话", example = "18888888888")
    private String phone;

    /** 用户电子邮箱 */
    @Schema(description = "电子邮箱", example = "123@qq,com")
    private String email;

    /** 用户头像URL或路径 */
    @Schema(description = "头像", example = "http://www.xxx.com/xxx.jpg")
    private String avatar;

    /** 用户角色标识 */
    @Schema(description = "角色", example = "user")
    private String role;
}
