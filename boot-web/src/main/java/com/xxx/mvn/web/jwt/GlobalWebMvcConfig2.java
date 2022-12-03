//package com.xxx.mvn.web.jwt;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * @author:TuoTuo
// * @createDate:2022/11/30 13:42
// * @description:
// */
//@Slf4j
//@Configuration
//public class GlobalWebMvcConfig2 extends WebMvcConfigurationSupport {
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        log.info("============");
//        //添加权限拦截器
//        registry.addInterceptor(new AuthenticationInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/static/**");
//////                .excludePathPatterns("/web/index/login");
//    }
//}
