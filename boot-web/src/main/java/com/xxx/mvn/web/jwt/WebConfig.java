//package com.xxx.mvn.web.jwt;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
///**
// * @author:TuoTuo
// * @createDate:2022/11/30 14:48
// * @description:
// */
//@Configuration
//public class WebConfig {
//    @Bean
//    public HandlerVoidMethod handlerVoidMethod() {
//        return new HandlerVoidMethod();
//    }
//
//    @Bean
//    public CustomizedHandlerAdapter handlerAdapter(HandlerVoidMethod handlerVoidMethod) {
//        CustomizedHandlerAdapter chl = new CustomizedHandlerAdapter();
//        chl.setCustomReturnValueHandlers(Arrays.asList(handlerVoidMethod));
//        return chl;
//    }
//}
