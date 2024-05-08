package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "服务预约")
public class Reserve implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    /** ID */
    @Schema(description = "ID")
    private Integer id;

    /** 服务ID */
    @Schema(description = "服务ID")
    private Integer serveId;

    /** 预约人ID */
    @Schema(description = "预约人ID")
    private Integer userId;

    /** 预约时间 */
    @Schema(description = "预约时间")
    private String time;

    /** 审核状态 */
    @Schema(description = "审核状态")
    private String status;

    /** 审核意见 */
    @Schema(description = "审核意见")
    private String reason;

    /** 服务名称 */
    @Schema(description = "服务名称")
    private String serveName;

    /** 预约人姓名 */
    @Schema(description = "预约人姓名")
    private String userName;
}
