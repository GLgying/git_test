package com.xxx.mvn.web.jwt;

import com.xxx.mvn.web.exception.BusinessException;
import com.xxx.mvn.web.exception.code.BaseResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author:TuoTuo
 * @createDate:2022/11/29 11:07
 * @description:
 */
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");
        /*从http请求头中取出token*/
//        final String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);
//        log.info("***"+handler);
//        if(handler instanceof ResourceHttpRequestHandler){
//            return true;
//        }
        System.out.println((handler instanceof HandlerMethod));
        /*如果不是映射到方法，直接通过  静态文件*/
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        /*如果是方法探测，直接通过*/
        if (HttpMethod.OPTIONS.equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        /*如果方法有JwtIgnore注解，直接通过*/
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(JwtIgnore.class)) {
            JwtIgnore jwtIgnore = method.getAnnotation(JwtIgnore.class);
            if (jwtIgnore.value()) {
                return true;
            }
        }
        if(StringUtils.isEmpty(token)){
            return true;
//            throw new BusinessException(BaseResponseCode.UNAUTHORIZED_ZERO_ERROR);
        }
        /*验证，并获取token内部信息*/
        String userToken = JwtTokenUtil.verifyToken(token);
        log.info("===="+userToken);
        /*将token放入本地缓存*/
        WebContextUtil.setUserToken(userToken);
        UserToken userToken1 = WebContextUtil.getUserToken();
        log.info("xx"+userToken1);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*方法结束后，移除缓存的token*/
        log.info("=====移除token===");
//        WebContextUtil.removeUserToken();
        UserToken userToken1 = WebContextUtil.getUserToken();
        log.info("x2x"+userToken1);
    }
}


//    /** * 登录 * @param userDto * @return */
//    @JwtIgnore@RequestMapping(value = "/login", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
//    public UserVo login(@RequestBody UserDto userDto, HttpServletResponse response){
//        //...参数合法性验证
//        // 从数据库获取用户信息
//        User dbUser = userService.selectByUserNo(userDto.getUserNo);
//        //....用户、密码验证    //创建token，并将token放在响应头
//        UserToken userToken = new UserToken();
//        BeanUtils.copyProperties(dbUser,userToken);
//        String token = JwtTokenUtil.createToken(JSONObject.toJSONString(userToken));
//        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, token);
//        //定义返回结果
//        UserVo result = new UserVo();
//        BeanUtils.copyProperties(dbUser,result);
//        return result;
//    }



