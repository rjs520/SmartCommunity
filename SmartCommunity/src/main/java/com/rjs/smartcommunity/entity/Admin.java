package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.io.Serial;
import lombok.Getter;
import lombok.Setter;

/**
 * 管理员实体类，继承自Account类并实现Serializable接口
 *
 * @author rjs
 */
@Getter
@Setter
@Schema(description = "管理员实体，包含管理员的基本信息")
public class Admin extends Account implements Serializable {

    @Serial
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "序列化版本号")
    private static final long serialVersionUID = 1L;

    /** 管理员ID */
    @Schema(description = "管理员ID", example = "1")
    private Integer id;

    /** 管理员用户名 */
    @Schema(description = "管理员用户名", example = "admin_username")
    private String username;

    /** 管理员密码 */
    @Schema(
            description = "管理员密码",
            example = "password123",
            accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    /** 管理员姓名 */
    @Schema(description = "管理员姓名", example = "Admin User")
    private String name;

    /** 管理员联系电话 */
    @Schema(description = "管理员联系电话", example = "1234567890")
    private String phone;

    /** 管理员电子邮箱 */
    @Schema(description = "管理员电子邮箱", example = "admin@example.com")
    private String email;

    /** 管理员头像URL或路径 */
    @Schema(description = "管理员头像URL或路径", example = "https://example.com/avatar.jpg")
    private String avatar;

    /** 管理员角色标识 */
    @Schema(description = "管理员角色标识", example = "ADMIN")
    private String role;
}
