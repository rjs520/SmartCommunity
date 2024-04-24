package com.rjs.smartcommunity.common;

import com.rjs.smartcommunity.common.enums.ResultCodeEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 响应结果对象类，用于封装HTTP接口返回的统一数据结构，包括状态码、消息、数据等内容。
 *
 * @author rjs
 */
@Setter
@Getter
public class Result {

    /** 响应状态码，通常为HTTP状态码或自定义错误码。 */
    private String code;

    /** 响应消息，对状态码的简短描述。 */
    private String msg;

    /** 响应数据，接口实际返回的具体内容，可以是任意类型的数据。 */
    private Object data;

    /**
     * 构造一个带有数据的响应结果对象。
     *
     * @param data 响应数据
     */
    private Result(Object data) {
        this.data = data;
    }

    /** 构造一个空响应结果对象。 */
    public Result() {}

    /**
     * 创建一个成功的响应结果，状态码和消息取自 {@link ResultCodeEnum#SUCCESS}。
     *
     * @return 成功的响应结果对象
     */
    public static Result success() {
        Result tResult = new Result();
        tResult.setCode(ResultCodeEnum.SUCCESS.code);
        tResult.setMsg(ResultCodeEnum.SUCCESS.msg);
        return tResult;
    }

    /**
     * 创建一个带有数据的成功响应结果，状态码和消息取自 {@link ResultCodeEnum#SUCCESS}。
     *
     * @param data 响应数据
     * @return 带有数据的成功响应结果对象
     */
    public static Result success(Object data) {
        Result tResult = new Result(data);
        tResult.setCode(ResultCodeEnum.SUCCESS.code);
        tResult.setMsg(ResultCodeEnum.SUCCESS.msg);
        return tResult;
    }

    /**
     * 创建一个表示系统错误的响应结果，状态码和消息取自 {@link ResultCodeEnum#SYSTEM_ERROR}。
     *
     * @return 系统错误的响应结果对象
     */
    public static Result error() {
        Result tResult = new Result();
        tResult.setCode(ResultCodeEnum.SYSTEM_ERROR.code);
        tResult.setMsg(ResultCodeEnum.SYSTEM_ERROR.msg);
        return tResult;
    }

    /**
     * 创建一个自定义错误码和消息的响应结果。
     *
     * @param code 自定义错误码
     * @param msg 自定义错误消息
     * @return 自定义错误的响应结果对象
     */
    public static Result error(String code, String msg) {
        Result tResult = new Result();
        tResult.setCode(code);
        tResult.setMsg(msg);
        return tResult;
    }

    /**
     * 创建一个基于给定 {@link ResultCodeEnum} 的响应结果。
     *
     * @param resultCodeEnum 结果码枚举对象
     * @return 基于枚举结果码的响应结果对象
     */
    public static Result error(ResultCodeEnum resultCodeEnum) {
        Result tResult = new Result();
        tResult.setCode(resultCodeEnum.code);
        tResult.setMsg(resultCodeEnum.msg);
        return tResult;
    }
}
