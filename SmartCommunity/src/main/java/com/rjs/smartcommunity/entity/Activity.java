package com.rjs.smartcommunity.entity;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 活动实体类
 *
 * @author rjs
 */
@Setter
@Getter
public class Activity implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    /** 活动ID */
    private Integer id;

    /** 活动名称 */
    private String name;

    /** 活动封面图片地址 */
    private String cover;

    /** 活动简介 */
    private String descr;

    /** 活动开始日期，格式为自定义字符串 */
    private String start;

    /** 活动结束日期，格式为自定义字符串 */
    private String end;

    /** 活动详细内容 */
    private String content;

    /** 活动举办地址 */
    private String address;
}
