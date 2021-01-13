package com.zgq.plainarticle.aop;

import cn.hutool.json.JSONUtil;
import com.xy.base.annotation.CacheRedis;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 缓存
 */
@Slf4j
@Aspect
@Component
@ConditionalOnClass(CacheRedis.class)
public class CacheRedisAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * @param point
     */
    @Around("@annotation(cacheRedis)")
    public Object around(ProceedingJoinPoint point, CacheRedis cacheRedis) throws Throwable {
        StringBuilder stringBuilder = new StringBuilder();
        String prefix = cacheRedis.prefix();
        stringBuilder.append(prefix);
        Object[] args = point.getArgs();
        for(Object o : args){
            stringBuilder.append(o);
            stringBuilder.append(":");
        }
        String key = stringBuilder.substring(0,stringBuilder.length()-1);
        log.info("redis key:{}",key);

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if( stringRedisTemplate.hasKey(key)){
            String str = stringRedisTemplate.opsForValue().get(key);
            return JSONUtil.toBean(str,method.getReturnType());
        }else{
            Object object = point.proceed(args);
            if( object != null ){
                stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(object));
            }
            return object;
        }
    }

}
