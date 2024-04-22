package com.rjs.smartcommunity.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import com.rjs.smartcommunity.common.Result;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器，使用@ControllerAdvice注解，作用于指定包下的所有Controller
 *
 * @author rjs
 */
@ControllerAdvice(basePackages = "com.rjs.smartcommunity.controller")
public class GlobalExceptionHandler {

    /** 日志记录器 */
    private static final Log log = LogFactory.get();

    /**
     * 统一处理未捕获的Exception异常，返回带有错误信息的JSON响应
     *
     * @param request 当前HTTP请求
     * @param e 发生的异常对象
     * @return Result对象，包含错误信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回json串
    public Result error(HttpServletRequest request, Exception e) {
        log.error("全局异常处理器捕获到异常信息：", e);
        // 返回默认错误信息
        return Result.error();
    }

    /**
     * 统一处理自定义的CustomException异常，返回带有特定错误状态码和消息的JSON响应
     *
     * @param request 当前HTTP请求
     * @param e 发生的CustomException异常对象
     * @return Result对象，包含自定义错误状态码和消息
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody // 返回json串
    public Result customError(HttpServletRequest request, CustomException e) {
        // 返回自定义错误状态码和消息
        return Result.error(e.getCode(), e.getMsg());
    }
}
