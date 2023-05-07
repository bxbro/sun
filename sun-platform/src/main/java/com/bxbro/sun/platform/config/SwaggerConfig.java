package com.bxbro.sun.platform.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description Swagger配置类
 * @Author dong
 * @Date 2020/11/21
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
//@Profile("!prod")
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sun is rising.")
                .description("this is the restful api document of Sun System")
                .contact(new Contact("danieldong", "https://github.com/Danieldong0802", "1756330108@qq.com"))
                .version("1.0.0")
                .build();
    }


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bxbro.sun.platform.controller"))
                .build();
    }


}
