package com.example.springboot.common;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//接口统一前缀
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        //指定controller统一的接口前缀   例如/api/admin
        configurer.addPathPrefix("/api",clazz -> clazz.isAnnotationPresent(RestController.class));
    }
}
