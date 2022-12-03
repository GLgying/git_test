package com.xxx.mvn.web.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler;

import java.util.stream.Collectors;

/**
 * @author:TuoTuo
 * @createDate:2022/11/30 14:47
 * @description:
 */
public class CustomizedHandlerAdapter extends RequestMappingHandlerAdapter {

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        setReturnValueHandlers(getReturnValueHandlers().stream().filter(
                h -> h.getClass() != ViewNameMethodReturnValueHandler.class
        ).collect(Collectors.toList()));
    }
}

