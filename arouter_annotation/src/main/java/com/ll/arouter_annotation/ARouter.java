package com.ll.arouter_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Arouter 注解定义
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface ARouter {
    /**
     * 路由路径
     *
     * @return
     */
    String path();

    /**
     * 路由分组
     *
     * @return
     */
    String group() default "";
}
