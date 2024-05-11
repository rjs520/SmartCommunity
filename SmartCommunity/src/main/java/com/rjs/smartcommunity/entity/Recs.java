package com.rjs.smartcommunity.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 反馈与建议提出表
 *
 * @author rjs
 */
@Setter
@Getter
public class Recs {
    /** ID */
    private Integer id;

    /** 反馈与建议id */
    private Integer csId;

    /** 用户id */
    private Integer userId;

    /** 反馈与建议内容 */
    private String content;

    /** 反馈与建议时间 */
    private String time;

    /** 反馈状态 */
    private String status;

    /** 回复意见 */
    private String reason;

    /** 反馈与建议标题 */
    private String csName;

    /** 用户名 */
    private String userName;
}
