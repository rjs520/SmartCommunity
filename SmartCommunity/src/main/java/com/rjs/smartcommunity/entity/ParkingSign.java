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
    @Schema(description = "ID", example = "1")
    private Integer id;

    /** 停车位id */
    @Schema(description = "停车位id", example = "1")
    private Integer parkingId;

    /** 手机号 */
    @Schema(description = "手机号", example = "18888888888")
    private String phone;

    /** 预约日期 */
    @Schema(description = "预约日期", example = "2021-07-01")
    private String date;

    /** 用户id */
    @Schema(description = "用户id", example = "1")
    private Integer userId;

    /** 审核状态 */
    @Schema(description = "审核状态", example = "待审核")
    private String status;

    /** 审核意见 */
    @Schema(description = "审核意见", example = "车位已被预约")
    private String reason;

    /** 预约人的用户名 */
    @Schema(description = "预约人的用户名", example = "张三")
    private String userName;

    /** 车位地址 */
    @Schema(description = "车位地址", example = "1号楼1单元101室")
    private String parkingAddress;
}
