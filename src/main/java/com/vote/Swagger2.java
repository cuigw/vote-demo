package com.vote;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 功能说明: Swagger2 构建RESTAPI<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2017年02月07日<br>
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.vote.web"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("投票系统REST API 接口文档")
                .description("请根据需要查看相应接口")
                .termsOfServiceUrl("http://139.129.47.102：8090/back/")
                .contact("cgw")
                .version("1.0")
                .build();
    }
}
