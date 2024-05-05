package com.rjs.smartcommunity.entity;

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
public class Comment implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    /** 评论ID */
    private Integer id;

    /** 评论内容 */
    private String content;

    /** 评论人ID */
    private Integer userId;

    /** 父级评论ID */
    private Integer pid;

    private Integer rootId;

    /** 评论时间 */
    private String time;

    /** 关联的实体ID，例如文章ID或帖子ID */
    private Integer fid;

    /** 评论所属的模块，例如文章、帖子等 */
    private String module;

    /** 子评论列表 */
    private List<Comment> children;

    /** 评论人名称 */
    private String userName;

    /** 评论人头像URL */
    private String avatar;

    /** 回复的用户名，用于显示评论是回复给谁的 */
    private String replyUser;
}
