package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 评论实体类
 *
 * @author rjs
 */
@Getter
@Setter
@Schema(description = "评论实体类")
public class Comment implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    /** 评论ID */
    @Schema(description = "评论ID", example = "1")
    private Integer id;

    /** 评论内容 */
    @Schema(description = "评论内容", example = "这是一条评论")
    private String content;

    /** 评论人ID */
    @Schema(description = "评论人ID", example = "1")
    private Integer userId;

    /** 父级评论ID */
    @Schema(description = "父级评论ID", example = "1")
    private Integer pid;

    /** 根评论ID */
    @Schema(description = "根评论ID", example = "1")
    private Integer rootId;

    /** 评论时间 */
    @Schema(description = "评论时间", example = "2021-01-01 12:00:00")
    private String time;

    /** 关联的实体ID，例如文章ID或帖子ID */
    @Schema(description = "关联的实体ID", example = "1")
    private Integer fid;

    /** 评论所属的模块，例如文章、帖子等 */
    @Schema(description = "评论所属的模块", example = "article")
    private String module;

    /** 子评论列表 */
    @Schema(description = "子评论列表", example = "[{}]")
    private List<Comment> children;

    /** 评论人名称 */
    @Schema(description = "评论人名称", example = "张三")
    private String userName;

    /** 评论人头像URL */
    @Schema(description = "评论人头像URL", example = "http://www.xxx.com/xxx.jpg")
    private String avatar;

    /** 回复的用户名，用于显示评论是回复给谁的 */
    @Schema(description = "回复的用户名", example = "李四")
    private String replyUser;
}
