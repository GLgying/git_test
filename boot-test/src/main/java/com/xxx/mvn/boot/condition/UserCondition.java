package com.xxx.mvn.boot.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * springboot  自动配置
 * @author:TuoTuo
 * @createDate:2022/7/19 20:57
 * @description:
 */
public class UserCondition implements Condition {

    /**
     * 控制 bean 是否加载 和加载时机
     * @param conditionContext
     * @param annotatedTypeMetadata
     * @return true 加载  false 不加载
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        //导入redis坐标后创建bean 判断某一个jar是否加载  加载了 在进行 加载User类
        Class<?> redisClass = null;
        boolean flag = false;
//        try {
//            redisClass = Class.forName("org.springframework.data.redis.core.RedisTemplate");
//            flag = true;
//        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
//            flag = false;
//        }
//        System.out.println("  "+redisClass);
        Map<String, Object> annotationAttributes = annotatedTypeMetadata.getAnnotationAttributes(ConditionOnClass.class.getName());
//        System.out.println(annotatedTypeMetadata);
//        for (Map.Entry<String, Object> stringObjectEntry : annotationAttributes.entrySet()) {
//            System.out.println(stringObjectEntry);
//        }
        String[] value = (String[]) annotationAttributes.get("value");
        for (String className : value) {
            System.out.println(className);
            if(className.contains("RedisTemplate")){
                return true;
            }
        }
        return false;
    }
}
