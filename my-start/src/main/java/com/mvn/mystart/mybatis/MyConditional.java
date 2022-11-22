package com.mvn.mystart.mybatis;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * 条件装配
 * @author Ging
 */
@Target({ElementType.TYPE, ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(value = MyonBeanConditional.class)
public @interface MyConditional {
    String value() default "admin";
}
