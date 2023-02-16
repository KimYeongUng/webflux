package com.example.webflux.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class AspectV1 {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping(){

    }

    @Before("getMapping()")
    public void before(JoinPoint point){
        log.info("[Log Before],{}",point.getSignature().getName());
    }

    @AfterReturning(pointcut = "getMapping()",returning = "result")
    public void after(JoinPoint point,Object result){
        log.info("[Log After] {}",point.getSignature().getName());
    }

    @Around("getMapping()")
    public Object around(ProceedingJoinPoint point)throws Throwable{
        log.info("Around Logging");
        try {
            log.info("END");
            return point.proceed();
        } catch (Exception e){
            log.info("ERROR");
            log.error(e.getMessage());
            return null;
        }
    }
}
