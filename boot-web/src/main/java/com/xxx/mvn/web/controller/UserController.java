package com.xxx.mvn.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.xxx.mvn.web.jwt.JwtIgnore;
import com.xxx.mvn.web.jwt.JwtTokenUtil;
import com.xxx.mvn.web.jwt.UserToken;
import com.xxx.mvn.web.util.DataResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:TuoTuo
 * @createDate:2022/11/28 16:23
 * @description:
 */
@Slf4j
@Api(tags = "组织模块-用户管理")
@RequestMapping("/sys")
@Controller
public class UserController {


    @ResponseBody
    @PostMapping(value = "/user/login")
    @JwtIgnore
    @ApiOperation(value = "用户登录接口")
    public DataResult test(HttpServletRequest request, HttpServletResponse response){
        DataResult result=DataResult.success();
        //....用户、密码验证    //创建token，并将token放在响应头
        UserToken userToken = new UserToken();
//        BeanUtils.copyProperties(new User("admin","1234"),userToken);
        userToken.setUserId("111");
        userToken.setUserName("222");
        String token = JwtTokenUtil.createToken(JSONObject.toJSONString(userToken));
//        String header = response.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, token);
        log.info("===登录成功===");
        return result;
    }




}

