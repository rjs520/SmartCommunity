package com.rjs.smartcommunity.entity;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 投诉与建议实体类
 *
 * @author rjs
 */
@Setter
@Getter
public class ComplaintSuggestion implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Integer id;

    /** 投诉或建议的标题 */
    private String title;

    /** 投诉或建议的详细内容 */
    private String content;

    /** 提出投诉或建议的用户ID */
    private Integer userId;

    /** 处理状态（例如：未处理，处理中，已处理） */
    private String status;

    /** 对投诉或建议的回复内容 */
    private String reply;

    /** 提出投诉或建议的用户姓名 */
    private String userName;
}
