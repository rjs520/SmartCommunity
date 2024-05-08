package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色用户父类实体
 *
 * @author rjs
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色用户父类实体")
public class Account {

    /** 账号ID */
    @Schema(description = "账号ID", example = "1")
    private Integer id;

    /** 用户名 */
    @Schema(description = "用户名", example = "rjs")
    private String username;

    /** 姓名 */
    @Schema(description = "名称（如真实姓名）", example = "Real JS")
    private String name;

    /** 密码 */
    @Schema(hidden = true, description = "密码")
    private String password;

    /** 角色 */
    @Schema(description = "角色标识（如'ADMIN', 'USER'等）", example = "USER")
    private String role;

    /** 新的密码 */
    @Schema(hidden = true, description = "新密码（用于密码修改操作）")
    private String newPassword;

    /** 头像 */
    @Schema(description = "头像URL或路径", example = "https://example.com/avatar.jpg")
    private String avatar;

    /** token */
    @Schema(description = "认证Token", example = "abcdefg123456")
    private String token;
}
