package com.snwolf.api.annotation;

import cn.hutool.core.annotation.Alias;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRole {

    @Alias("value")
    String[] role() default "";

    String[] value();
}
