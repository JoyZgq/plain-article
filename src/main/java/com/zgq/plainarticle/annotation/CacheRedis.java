package com.zgq.plainarticle.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author RD_KXY
 * @Date 2020/5/20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface CacheRedis {
    /**
     * key前缀
     * @return
     */
    String prefix();
}
