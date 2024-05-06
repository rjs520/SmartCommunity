package com.rjs.smartcommunity.common.enums;

import lombok.Getter;

/**
 * 活动状态枚举类，表示活动的四种状态：未开始、已结束、未报名和已报名。
 *
 * @author rjs
 */
@Getter
public enum ActivityStatusEnum {
    // 表示活动尚未开始
    NOT_START("未开始"),
    // 表示活动尚未开始
    IS_END("已结束"),
    // 表示用户未报名参加活动
    NOT_SIGN("未报名"),
    // 表示用户已报名参加活动
    IS_SIGN("已报名");
    // 存储状态的描述信息

    private final String status;

    /**
     * 构造方法，用于初始化枚举实例的状态描述。
     *
     * @param status 状态描述字符串
     */
    ActivityStatusEnum(String status) {
        this.status = status;
    }
}
