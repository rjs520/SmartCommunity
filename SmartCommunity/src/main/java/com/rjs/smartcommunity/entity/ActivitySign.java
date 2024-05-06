package com.rjs.smartcommunity.entity;

import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 活动报名实体类
 *
 * @author rjs
 * @version 1.0
 */
@Getter
@Setter
public class ActivitySign implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    /** 报名实体类的ID */
    private Integer id;

    /** 对应的活动ID */
    private Integer activityId;

    /** 报名者的联系方式 */
    private String phone;

    /** 报名日期 */
    private String date;

    /** 报名人的用户ID */
    private Integer userId;

    /** 报名的审核状态 */
    private String status;

    /** 审核不通过的原因 */
    private String reason;

    private String userName;
}
