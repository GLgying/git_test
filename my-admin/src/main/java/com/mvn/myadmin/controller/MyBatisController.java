package com.mvn.myadmin.controller;

import com.mvn.mystart.mybatis.MyBatisConnection;
import com.mvn.mystart.mybatis.MyBatisTemplate;
import com.mvn.mystart.mybatis.MyConditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWarDeployment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyBatisController {
    @Autowired
    public MyBatisTemplate myBatisTemplate;
//    @Autowired(required=false)
    @Autowired
    public MyBatisConnection myBatisConnection;

    @ResponseBody
    @RequestMapping("mybatis")
    public String mybatis(){
        myBatisConnection.connection();
        myBatisTemplate.insert();
        return "45";
    }

    @RequestMapping("x")
    public String x(@Validated String name){
        System.out.println("2");
        return "45";
    }


}
