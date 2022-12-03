package com.mvm.boot.config;

import com.alibaba.fastjson.JSONObject;
import com.mvm.boot.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:TuoTuo
 * @createDate:2022/12/2 20:39
 * @description:
 */
@Slf4j
@Configuration
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("======成功处理器======");
        response.setContentType("application/json;charset=UTF-8");
        String resp = JSONObject.toJSONString(BaseResponse.success("登录成功"));
        log.info(resp);
        response.getWriter().println(resp);
    }


}
