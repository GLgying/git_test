package com.mvn.mystart.mybatis;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 条件装配  匹配
 * @author Ging
 */
public class MyonBeanConditional implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
      //ture 装配 false 不装配
        String value = (String) metadata.getAnnotationAttributes("com.mvn.mystart.mybatis.MyConditional").get("value");
        if(value.equals("admin")){
            System.out.println("*-*-*");
            return true;
        }
        return false;
    }
}
