package com.xxx.mvn.mq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:TuoTuo
 * @createDate:2022/8/25 20:22
 * @description:
 */
@RestController
public class IndexController {
    @RequestMapping("")
    public String index(){
        return "Hello Word";
    }
}
