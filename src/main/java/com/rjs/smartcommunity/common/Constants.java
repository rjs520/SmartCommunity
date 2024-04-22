package com.rjs.smartcommunity.common;

/**
 * 公共常量接口，定义了项目中通用的、不随环境变化而变化的字符串常量。
 *
 * @author rjs
 */
public interface Constants {

    /** token关键字，用于在HTTP请求的Header或参数中传递JWT令牌。 */
    String TOKEN = "token";

    /** 用户默认密码，当创建新用户时，若未指定密码，则使用此默认密码。 */
    String USER_DEFAULT_PASSWORD = "123";
}
