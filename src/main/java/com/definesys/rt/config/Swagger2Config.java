package com.definesys.rt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@CrossOrigin  //跨域支持
public class Swagger2Config {
    @Bean
    public Docket demoApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.definesys.rt.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());

    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("人天费用报告管理系统")
                .description("上海得帆信息技术有限公司，http://www.definesys.com")
                .termsOfServiceUrl("http://www.definesys.com")
                .version("1.1")
                .build();
    }
}
