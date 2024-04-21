package com.rjs.smartcommunity.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/** Spring MVC 配置类
 * @author rjs*/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /** 注入自定义的JWT拦截器对象 */
    @Resource private JwtInterceptor jwtInterceptor;

    /**
     * 添加自定义拦截器到Spring MVC框架中，并设置其拦截规则。
     *
     * <p>此处将JWT拦截器添加到全局拦截链中，对所有路径（`/**`）进行拦截， 但排除以下路径，即这些路径不受JWT拦截器的影响：
     *
     * <ul>
     *   <li>`/`：根路径
     *   <li>`/login`：登录接口
     *   <li>`/register`：注册接口
     *   <li>`/files/**`：文件资源相关路径
     * </ul>
     *
     * @param registry 拦截器注册器，用于管理Spring MVC中的拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/")
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/files/**");
    }
}
