package com.xxx.mvn.boot.controller;

import com.xxx.mvn.boot.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:TuoTuo
 * @createDate:2022/7/18 21:33
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String hello(){
        log.info("log:{},{}","ss","dd");
        log.info("log:{},{}","ss","dsd");
        return "hello";
    }
//    @Value("${name}")
//    String name;

//    @Value("${person.name}")
//    String name2;

//    @Value("${user[1]}")
//    String user1;
//
//    @Autowired
//    Environment environment;

    @Autowired
    Person person;

    @RequestMapping("/index")
    public String index(){
//        log.info(name);
//        log.info(name2);
//        log.info(environment.getProperty("name"));
//        log.info(environment.getProperty("person.name"));
//        log.info(environment.getProperty("user[2]"));
//        log.info(user1);
//        log.info(person.toString());
        return "Hello";
    }
}
