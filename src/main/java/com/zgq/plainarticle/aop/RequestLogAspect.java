package com.zgq.plainarticle.aop;

import cn.hutool.json.JSONUtil;
import com.xy.base.annotation.RequestLog;
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
 * 请求日志
 */
@Slf4j
@Aspect
@Component
@ConditionalOnClass(RequestLog.class)
public class RequestLogAspect {

    /**
     * @param point
     */
    @Around("@annotation(requestLog)")
    public Object around(ProceedingJoinPoint point, RequestLog requestLog) throws Throwable {
        HttpServletRequest request  = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Long startTime = System.currentTimeMillis();

        String requestUrl = request.getRequestURI();
        String requestMethod = request.getMethod();
        Object[] args = point.getArgs();
        //获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        //目标类、方法
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        Object object = point.proceed();
        if( requestLog.print() ){
            log.info("{}-url:{};method:{};class:{};method:{};args:{};time:{} ms",requestLog.value()
                    ,requestUrl,requestMethod,className,methodName,JSONUtil.toJsonStr(args),System.currentTimeMillis() - startTime);
        }
        return object;
    }

}
