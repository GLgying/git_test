package com.mvm.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:TuoTuo
 * @createDate:2022/11/30 22:25
 * @description:
 */
@RequestMapping("/test")
@RestController
public class IndexController {

    @RequestMapping("/hello")
    public String todo(){
        return "hello security";
    }
}
