package com.definesys.rt.config;

import com.definesys.rt.intercepter.JWTIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTIntercepter())
                .addPathPatterns("/**") //拦截路径
                .excludePathPatterns("/login","/register","/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"); //放行路径
    }
//
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html");
//        registry.addResourceHandler("/webjars/**");
//    }
}
