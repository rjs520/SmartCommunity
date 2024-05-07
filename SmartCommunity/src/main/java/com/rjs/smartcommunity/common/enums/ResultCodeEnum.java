package com.rjs.smartcommunity.common.enums;

/**
 * 自定义响应结果枚举类，封装了常见的HTTP状态码和业务相关的错误码及其对应的描述信息。
 *
 * @author rjs
 */
public enum ResultCodeEnum {
    /** 成功（HTTP状态码200） */
    SUCCESS("200", "成功"),

    /** 参数异常（HTTP状态码400） */
    PARAM_ERROR("400", "参数异常"),
    /** 无效的token（HTTP状态码401） */
    TOKEN_INVALID_ERROR("401", "无效的token"),
    /** token验证失败，请重新登录（HTTP状态码401） */
    TOKEN_CHECK_ERROR("401", "token验证失败，请重新登录"),
    /** 参数缺失（自定义错误码4001） */
    PARAM_LOST_ERROR("4001", "参数缺失"),

    /** 系统异常（HTTP状态码500） */
    SYSTEM_ERROR("500", "系统异常"),
    /** 用户名已存在（自定义错误码5001） */
    USER_EXIST_ERROR("5001", "用户名已存在"),
    /** 用户未登录（自定义错误码5002） */
    USER_NOT_LOGIN("5002", "用户未登录"),
    /** 账号或密码错误（自定义错误码5003） */
    USER_ACCOUNT_ERROR("5003", "账号或密码错误"),
    /** 用户不存在（自定义错误码5004） */
    USER_NOT_EXIST_ERROR("5004", "用户不存在"),
    /** 原密码输入错误（自定义错误码5005） */
    PARAM_PASSWORD_ERROR("5005", "原密码输入错误"),
    /** 已报名（自定义错误码5006） */
    SIGN_EXIST_ERROR("5006", "已报名"),
    APPOINTMENT_EXIST_ERROR("5007", "已预约");

    /** 错误码（HTTP状态码或自定义错误码） */
    public final String code;

    /** 错误描述信息 */
    public final String msg;

    /**
     * 构造一个ResultCodeEnum实例，初始化其code和msg字段。
     *
     * @param code 错误码
     * @param msg 错误描述信息
     */
    ResultCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
