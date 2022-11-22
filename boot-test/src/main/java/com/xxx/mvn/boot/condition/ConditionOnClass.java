package com.xxx.mvn.boot.condition;


import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * springboot  自动配置
 * @author Ging
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional({UserCondition.class})
public @interface ConditionOnClass {
    String[] value();
}
