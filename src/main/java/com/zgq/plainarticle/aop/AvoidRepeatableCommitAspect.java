package com.zgq.plainarticle.aop;

import cn.hutool.core.util.StrUtil;
import com.zgq.plainarticle.annotation.AvoidRepeatableCommit;
import com.zgq.plainarticle.common.Response;
import com.zgq.plainarticle.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 重复提交aop
 */
@Slf4j
@Aspect
@Component
@ConditionalOnClass(AvoidRepeatableCommit.class)
public class AvoidRepeatableCommitAspect {

    /**
     * @param point
     */
    @Around("@annotation(repeatableCommit)")
    public Response around(ProceedingJoinPoint point, AvoidRepeatableCommit repeatableCommit) throws Throwable {

        HttpServletRequest request  = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = IpUtil.getIpAddr(request);
        //获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //目标类、方法
        String className = method.getDeclaringClass().getName();
        String name = method.getName();
        String ipKey = String.format("%s#%s",className,name);
        int hashCode = Math.abs(ipKey.hashCode());
        String key = String.format("%s_%d",ip,hashCode);
        log.info("ipKey={},hashCode={},key={}",ipKey,hashCode,key);
//        AvoidRepeatableCommit avoidRepeatableCommit =  method.getAnnotation(AvoidRepeatableCommit.class);
        long timeout = repeatableCommit.timeout();
        if (timeout < 0){
            //过期时间5分钟
            timeout = 60*5;
        }
//        String value =  stringRedisTemplate.opsForValue().get(key);
        String value = "";
        if (StrUtil.isNotEmpty(value)){
            return new Response("请勿重复提交");
        }
//        stringRedisTemplate.opsForValue().set(key, UUID.randomUUID().toString(),timeout, TimeUnit.MILLISECONDS);
        //执行方法
        Object object = point.proceed();
        return (Response) object;
    }

}
