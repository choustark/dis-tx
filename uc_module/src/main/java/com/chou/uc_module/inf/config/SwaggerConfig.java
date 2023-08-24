package com.chou.uc_module.inf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Axel
 * @version 1.0
 * @className SwaggerConfig
 * @description swagger-ui 配置
 * @date 2022/10/2 22:45
 */

@Configuration
@EnableOpenApi
public class SwaggerConfig implements WebMvcConfigurer  {

    @Bean
    public Docket docket(){
        Docket docket = new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo()).enable(true)
                .select()
                //apis： 添加swagger接口提取范围
                .apis(RequestHandlerSelectors.basePackage("com.chou.uc_module.controller"))
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("用户模块接口")
                .description("主要涉及用户、角色、组织、权限等接口信息")
                .contact(new Contact("Chou.stark",
                        "https://juejin.cn/user/3245414057784158",
                        "1601459351@qq.com"))
                .version("v1.0.0")
                .build();

    }
}
