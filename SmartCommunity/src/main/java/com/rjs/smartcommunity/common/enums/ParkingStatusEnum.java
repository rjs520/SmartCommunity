package com.rjs.smartcommunity.common.enums;

import lombok.Getter;

/**
 * 预约状态枚举类，表示预约的四种状态：未开始、已结束、未报名和已报名。
 *
 * @author rjs
 */
@Getter
public enum ParkingStatusEnum {
    // 表示未预约
    NOT_APPOINTMENT("未预约"),
    // 表示预约尚未开始
    IS_APPOINTMENT("已预约"),
    // 车位已被使用
    APPOINTMENT("已被使用");

    private final String status;

    /**
     * 构造方法，用于初始化枚举实例的状态描述。
     *
     * @param status 状态描述字符串
     */
    ParkingStatusEnum(String status) {
        this.status = status;
    }
}
