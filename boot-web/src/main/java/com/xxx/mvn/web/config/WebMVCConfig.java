//package com.xxx.mvn.web.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//@Slf4j
//@Configuration
//public class WebMVCConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        log.info("22");
//        //跨域配置
//        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080");
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        log.info("1111");
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**");
//    }
//}
//
