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
public class TimeFlyAspect {
    private Logger log  = LoggerFactory.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(com.example.annotation.TimeFly)")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        startTime.set(System.currentTimeMillis());
    }

    //参数是object，别手贱打成了objects
    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        log.info("SPEND TIME :" + (1.0*System.currentTimeMillis()-startTime.get()/1000) +" 秒");
    }

    /*@Around("webLog()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("111");
    }*/
}
