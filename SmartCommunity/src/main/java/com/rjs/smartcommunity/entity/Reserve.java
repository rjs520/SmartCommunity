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
    @Schema(description = "ID", example = "1")
    private Integer id;

    /** 服务ID */
    @Schema(description = "服务ID", example = "1")
    private Integer serveId;

    /** 预约人ID */
    @Schema(description = "预约人ID", example = "1")
    private Integer userId;

    /** 预约时间 */
    @Schema(description = "预约时间", example = "2021-07-01 12:00:00")
    private String time;

    /** 审核状态 */
    @Schema(description = "审核状态", example = "待审核")
    private String status;

    /** 审核意见 */
    @Schema(description = "审核意见", example = "服务已被预约")
    private String reason;

    /** 服务名称 */
    @Schema(description = "服务名称", example = "维修")
    private String serveName;

    /** 预约人姓名 */
    @Schema(description = "预约人姓名", example = "张三")
    private String userName;
}
