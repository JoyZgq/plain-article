package com.zgq.plainarticle.aop;


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.xy.base.common.Response;
import com.xy.base.enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 验签
 */
@Aspect
@Component
@Slf4j
public class SignAspect {
    /**
     * 入方法前验签
     * @param joinPoint
     */
    @Around("execution(public * com.xy.*.controller..*.*(..))")
    public Response around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String signStr = request.getHeader("sign");
        String timestamp = request.getHeader("timestamp");

        //签名-test
        log.info("sign:{}",DigestUtil.md5Hex("Aa"+timestamp));

        if(StrUtil.isEmpty( signStr )
            || StrUtil.isEmpty( timestamp )
            || !StrUtil.equals(signStr,DigestUtil.md5Hex("Aa"+timestamp) ) ){
            return new Response(CodeEnum.SIGN_ERROR);
        }
        return (Response) joinPoint.proceed();
    }
}
