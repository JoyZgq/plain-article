package com.zgq.plainarticle.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 避免重复提交
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AvoidRepeatableCommit {

    /**
     * 指定时间内不可重复提交,单位毫秒
     * @return
     */
    long timeout()  default 2000 ;
}
