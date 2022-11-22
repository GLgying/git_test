package com.mvn.mystart.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

/**
 * 具体实现 模板
 * @author Ging
 */
@ComponentScan
public class MyBatisTemplate {

    public void insert(){
        System.out.println("添加成功");
    }
}
