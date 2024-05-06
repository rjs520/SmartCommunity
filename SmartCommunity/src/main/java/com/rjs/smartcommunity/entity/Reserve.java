package com.rjs.smartcommunity.entity;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 服务预约
 *
 * @author rjs
 */
@Getter
@Setter
public class Reserve implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    /** ID */
    private Integer id;

    /** 服务ID */
    private Integer serveId;

    /** 预约人ID */
    private Integer userId;

    /** 预约时间 */
    private String time;

    /** 审核状态 */
    private String status;

    /** 审核意见 */
    private String reason;

    /** 服务名称 */
    private String serveName;

    /** 预约人姓名 */
    private String userName;
}
