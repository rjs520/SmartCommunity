package com.rjs.smartcommunity.exception;

import com.rjs.smartcommunity.common.enums.ResultCodeEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常类，继承自RuntimeException
 *
 * @author rjs
 */
@Setter
@Getter
public class CustomException extends RuntimeException {

    /** 异常状态码 */
    private String code;

    /** 异常消息 */
    private String msg;

    /**
     * 使用ResultCodeEnum构造CustomException实例
     *
     * @param resultCodeEnum 结果码枚举对象，提供状态码和消息
     */
    public CustomException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.code;
        this.msg = resultCodeEnum.msg;
    }

    /**
     * 使用指定状态码和消息构造CustomException实例
     *
     * @param code 异常状态码
     * @param msg 异常消息
     */
    public CustomException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
