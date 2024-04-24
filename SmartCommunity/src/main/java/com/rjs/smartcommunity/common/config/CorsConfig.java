package com.rjs.smartcommunity.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置类
 *
 * @author rjs
 */
@Configuration
public class CorsConfig {

    /**
     * 创建并配置一个跨域过滤器（CorsFilter），允许来自任何源（origin）、携带任何头（header）和使用任何方法（method）的请求。
     *
     * @return 配置好地跨域过滤器实例
     */
    @Bean
    public CorsFilter corsFilter() {
        // 创建基于URL的Cors配置源
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // 创建Cors配置对象
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // 允许所有源访问
        // 1 设置访问源地址
        corsConfiguration.addAllowedOrigin("*");

        // 允许所有请求头
        // 2 设置访问源请求头
        corsConfiguration.addAllowedHeader("*");

        // 允许所有请求方法（GET、POST、PUT、DELETE等）
        // 3 设置访问源请求方法
        corsConfiguration.addAllowedMethod("*");

        // 将跨域配置应用到所有路径（"/**" 表示所有路径）
        // 4 对接口配置跨域设置
        source.registerCorsConfiguration("/**", corsConfiguration);

        // 返回配置好地跨域过滤器实例
        return new CorsFilter(source);
    }
}
