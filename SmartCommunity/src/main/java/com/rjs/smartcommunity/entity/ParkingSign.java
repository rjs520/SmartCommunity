package com.rjs.smartcommunity.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * 车位预约
 *
 * @author rjs
 */
@Setter
@Getter
@Schema(description = "车位预约")
public class ParkingSign implements Serializable {

    @Serial private static final long serialVersionUID = 1L;

    /** ID */
    @Schema(description = "ID")
    private Integer id;

    /** 停车位id */
    @Schema(description = "停车位id")
    private Integer parkingId;

    /** 手机号 */
    @Schema(description = "手机号")
    private String phone;

    /** 预约日期 */
    @Schema(description = "预约日期")
    private String date;

    /** 用户id */
    @Schema(description = "用户id")
    private Integer userId;

    /** 审核状态 */
    @Schema(description = "审核状态")
    private String status;

    /** 审核意见 */
    @Schema(description = "审核意见")
    private String reason;

    /** 预约人的用户名 */
    @Schema(description = "预约人的用户名")
    private String userName;

    /** 车位地址 */
    @Schema(description = "车位地址")
    private String parkingAddress;
}
