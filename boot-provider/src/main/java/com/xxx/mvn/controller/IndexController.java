package com.xxx.mvn.controller;

/**
 * @author:TuoTuo
 * @createDate:2022/8/4 19:36
 * @description:
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "HelloWorld";
    }
}
