package com.mvm.boot.controller;

//import com.mvm.boot.service.TestService;
import com.alibaba.fastjson.JSONObject;
import com.mvm.boot.entity.TestVo;
import com.mvm.boot.service.TestService;
import com.mvm.boot.util.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author:TuoTuo
 * @createDate:2022/12/2 19:35
 * @description:
 *
 * 1、/no-login 接口不需要认证就可以直接访问
 * 2、/no-authorize 需要认证 但不需要授权就可以访问
 * 3、/need-authorize 首先需要认证 认证通过还需要授权 这里需要校长的角色才可以访问该接口 但是我们测试用户只有教师和学生所以没有权限访问该接口
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    private TestService testService;
    /**
     * 1、不需要登陆就可以访问
     */
    @RequestMapping(value = "/no-login")
    public BaseResponse noLogin() {
        log.info("此次请求的接口：不需要登陆就可访问");
        int count = testService.count();
        log.info("查询条数:{}",count);
        List<TestVo> testVos = testService.selectAll();
        log.info(testVos.toString());
        log.info("查询结果:{}", JSONObject.toJSONString(String.valueOf(testVos)));
        return BaseResponse.success("欢迎访问不需要登陆接口");
    }
    /**
     * 2、只登陆，不许认证接口
     */
    @RequestMapping(value = "/no-authorize")
    public BaseResponse needAuthorize(){
        log.info("此次请求的接口：登陆即可访问");
        return BaseResponse.success("登陆了 不用授权");
    }
    /**
     * 3、登陆 + 相关认证接口
     */
    @RequestMapping(value = "/need-authorize")
    public BaseResponse noAuthorize() {
        log.info("此次请求的接口：需要登陆后再授权才能访问");
        return BaseResponse.success("登陆+授权成功");
    }
    /**
     * @Description: 如果自动跳转到这个页面，说明用户未登录，返回相应的提示即可
     */
    @RequestMapping("/login_page")
    public BaseResponse loginPage() {
        log.info("此次请求的接口：需要去登录");
        return  BaseResponse.fail("尚未登录，请登录!");
    }
}
