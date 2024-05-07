package com.rjs.smartcommunity.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 车位预约
 *
 * @author rjs
 */
@Setter
@Getter
public class ParkingSign {
    /** ID */
    private Integer id;

    /** 停车位id */
    private Integer parkingId;

    /** 手机号 */
    private String phone;

    /** 预约日期 */
    private String date;

    /** 用户id */
    private Integer userId;

    /** 审核状态 */
    private String status;

    /** 审核意见 */
    private String reason;

    /** 预约人的用户名 */
    private String userName;

    /** 车位地址 */
    private String parkingAddress;
}
