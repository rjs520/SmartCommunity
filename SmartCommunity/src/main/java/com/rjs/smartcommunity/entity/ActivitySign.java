package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 活动报名实体类
 *
 * @author rjs
 * @version 1.0
 * @see Serializable
 * @see Schema
 */
@Getter
@Setter
@Schema(description = "表示活动报名的实体类")
public class ActivitySign implements Serializable {
    @Serial
    @Schema(hidden = true, description = "序列化ID")
    private static final long serialVersionUID = 1L;

    /** 报名实体类的唯一标识 */
    @Schema(description = "报名实体类的唯一标识", example = "1")
    private Integer id;

    /** 对应的活动ID */
    @Schema(description = "对应的活动ID", example = "1")
    private Integer activityId;

    /** 报名者的联系电话 */
    @Schema(description = "报名者的联系电话", example = "12345678901")
    private String phone;

    /** 报名日期 */
    @Schema(description = "报名的日期", example = "2021-01-01")
    private String date;

    /** 报名者的id */
    @Schema(description = "报名人的用户ID", example = "1")
    private Integer userId;

    /** 报名的审核状态 */
    @Schema(description = "报名的审核状态（例如：待审核，审核拒绝）", example = "待审核")
    private String status;

    /** 审核不通过时的原因 */
    @Schema(description = "审核不通过时的原因", example = "报名人数已满")
    private String reason;

    /** 报名者的用户名 */
    @Schema(description = "报名人的用户名", example = "张三")
    private String userName;

    /** 活动的名称 */
    @Schema(description = "活动的名称", example = "春游")
    private String activityName;
}
