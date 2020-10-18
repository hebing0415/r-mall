package com.robot.home.annotation;

import java.lang.annotation.*;

/**
 * 日志注解
 */
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {
    String description() default "";
}

