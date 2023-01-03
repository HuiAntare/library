package com.example.springboot.common;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//接口统一前缀
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        //指定controller统一的接口前缀   例如/api/admin
        configurer.addPathPrefix("/api",clazz -> clazz.isAnnotationPresent(RestController.class));
    }

    // 加自定义拦截器JwtInterceptor，设置拦截规则
    //.excludePathPatterns("/api/admin/login"); 放开登录接口,因为登录时还没有token
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有api开头的请求,进入拦截器里面验证 当return true时才能访问数据
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**").excludePathPatterns("/api/admin/login");
    }
}
