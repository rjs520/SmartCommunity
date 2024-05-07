package com.rjs.smartcommunity.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * Spring MVC 配置类，用于定制Spring MVC的额外功能，例如拦截器等。
 *
 * @author rjs
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /** 注入自定义的JWT拦截器对象，用于后续配置中。 该注解会将名为jwtInterceptor的Bean注入到当前类的成员变量中。 */
    @Resource private JwtInterceptor jwtInterceptor;

    /**
     * 配置自定义拦截器，添加到Spring MVC的拦截器链中。 本方法用于设置拦截器的拦截规则，指定哪些请求需要被拦截，以及排除哪些请求不需要被拦截。
     *
     * @param registry 拦截器注册器，提供方法以添加和配置拦截器。 通过调用其方法，可以指定拦截器拦截的路径和排除的路径。
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将JWT拦截器添加到拦截器链中，并设置拦截和排除的路径规则
        registry.addInterceptor(jwtInterceptor)
                // 指定所有路径都受JWT拦截器拦截
                .addPathPatterns("/**")
                // 但排除根路径
                .excludePathPatterns("/")
                // 排除登录接口路径
                .excludePathPatterns("/login")
                // 排除注册接口路径
                .excludePathPatterns("/register")
                // 排除所有文件资源相关路径
                .excludePathPatterns("/files/**")
                // 排除SpringDoc的路径
                .excludePathPatterns("/swagger-ui/**")
                .excludePathPatterns("/v3/api-docs/**")
                .excludePathPatterns("/swagger-ui.html");
    }
}
