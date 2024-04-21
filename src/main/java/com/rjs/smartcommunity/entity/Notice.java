package com.rjs.smartcommunity.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/** 公告信息实体类，实现Serializable接口
 * @author rjs*/
@Setter
@Getter
public class Notice implements Serializable {

    /** 序列化版本号 */
    private static final long serialVersionUID = 1L;

    /** 公告信息ID */
    private Integer id;

    /** 公告标题 */
    private String title;

    /** 公告内容 */
    private String content;

    /** 公告创建时间 */
    private String time;

    /** 公告创建人 */
    private String user;
}
