
package com.mvn.mystart.mybatis;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 监听器
 * @author Ging
 */
public class MyListener2 implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("监听器2starter...");
    }
}
