package com.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

@Aspect
@Component
public class WebLogAspect {
    private Logger log  = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.example.annotation.TimeFly)")
    public void webLog(){}

    /*@Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        startTime.set(System.currentTimeMillis());
    }*/

    @AfterReturning(pointcut = "webLog()",returning = "ret")
    public void doAfterReturning(Objects ret) throws  Throwable{
        log.info("SPEND TIME :" + (System.currentTimeMillis()-startTime.get()));
    }

    /*@Around("webLog()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("111");
    }*/
}
