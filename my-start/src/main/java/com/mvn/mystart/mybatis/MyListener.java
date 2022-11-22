package com.mvn.mystart.mybatis;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听器
 * @author Ging
 */
public class MyListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.getClass());
        System.out.println(event.getSource());
        System.out.println(event.getTimestamp());
        System.out.println("监听器1starter...");
    }
}
