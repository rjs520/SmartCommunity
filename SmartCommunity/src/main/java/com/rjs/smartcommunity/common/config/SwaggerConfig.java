package com.rjs.smartcommunity.common.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置类，用于配置OpenAPI文档的相关信息。
 *
 * @author rjs
 */
@Configuration
public class SwaggerConfig {

    /**
     * 配置公开的API分组。
     *
     * @return GroupedOpenApi对象，定义了API的分组和要扫描的包路径。
     */
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                // API分组标识
                .group("rjs")
                // 要扫描的包路径
                .packagesToScan("com.rjs.smartcommunity")
                .build();
    }

    /**
     * 配置OpenAPI文档的基本信息。
     *
     * @return OpenAPI对象，包含了API的标题、描述、版本和许可信息，以及外部文档链接。
     */
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(
                        new Info()
                                // API标题
                                .title("智慧社区系统API文档")
                                // API描述
                                .description("任俊松的智慧社区系统API文档")
                                // API版本
                                .version("v1.0.0")
                                .license(
                                        new License()
                                                // 许可证名称
                                                .name("Apache 2.0")
                                                // 许可证URL
                                                .url("http://springdoc.org")))
                .externalDocs(
                        new ExternalDocumentation()
                                // 外部文档描述
                                .description("SpringShop Wiki Documentation")
                                // 外部文档URL
                                .url("https://springshop.wiki.github.org/docs"));
    }
}
