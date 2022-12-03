package com.mvm.boot.config;

import com.alibaba.fastjson.JSONObject;
import com.mvm.boot.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:TuoTuo
 * @createDate:2022/12/2 20:55
 * @description:
 */
@Slf4j
@Configuration
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("======失败处理器======");
        response.setContentType("application/json;charset=UTF-8");
        String resp = JSONObject.toJSONString(BaseResponse.fail("登录失败"));
        log.info(resp);
        response.getWriter().println(resp);
    }
}
