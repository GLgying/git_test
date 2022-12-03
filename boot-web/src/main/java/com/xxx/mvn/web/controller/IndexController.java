package com.xxx.mvn.web.controller;

import com.xxx.mvn.web.jwt.JwtIgnore;
import com.xxx.mvn.web.jwt.JwtTokenUtil;
import com.xxx.mvn.web.util.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;

/**
 * @author:TuoTuo
 * @createDate:2022/11/25 22:00
 * @description:
 */
@Slf4j
@RequestMapping("/index")
@Controller
public class IndexController {

    @RequestMapping("/login")
    /** * 登录 * @param userDto * @return */
    @JwtIgnore
//    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public String test(){
        return "login";
    }

//    @ResponseBody
//    @JwtIgnore
    @RequestMapping("/home")
    public ModelAndView  home(HttpServletRequest request, HttpServletResponse response, Model model,ModelAndView mv) throws Exception {
        String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.info("home"+token);
        log.info("home: to test");
//        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, token);
//        model.addAttribute(JwtTokenUtil.AUTH_HEADER_KEY, token);
//
//        HttpSession session = request.getSession();
//        System.out.println(session);
        mv.setViewName("test");
        return mv;
    }

    @RequestMapping("/main")
    public String inx(HttpServletRequest request, HttpServletResponse response){
        return "main";
    }

    @RequestMapping("/layui")
    public String layui(){
        return "layui";
    }
}








