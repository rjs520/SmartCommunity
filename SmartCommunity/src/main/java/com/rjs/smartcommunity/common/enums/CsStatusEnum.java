package com.rjs.smartcommunity.common.enums;

import lombok.Getter;

/**
 * @author rjs
 */
@Getter
public enum CsStatusEnum {
    // 未回复
    NOT_REPLY("未回复"),
    // 已回复
    REPLIED("已回复"),
    // 已拒绝
    REJECTED("已拒绝");

    private final String status;

    /**
     * 构造方法，用于初始化枚举实例的状态描述。
     *
     * @param status 状态描述字符串
     */
    CsStatusEnum(String status) {
        this.status = status;
    }
}
