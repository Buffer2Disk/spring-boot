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

    /*如果在同一接入点（join point) 有多个通知（advice）
如果两个 advice 位于同一 aspect 内，且执行顺序有先后，通过 advice 的声明顺序是无法确定其执行顺序的，比如 Before 和 Around, 因为 advice 方法的声明顺序无法通过反射获取，只能采取如下变通方式，二选一：
将两个 advice 合并为一个 advice，那么执行顺序就可以通过代码控制了
将两个 advice 分别抽离到各自的 aspect 内，然后为 aspect 指定执行顺序
*/
  /*
  如果两个advice位于不同aspect内，可以用@Order(i) 注解来标识切面的优先级，    i越小，优先级越高，
  在切入点前的操作，按order的值由小到大执行
  在切入点后的操作，按order的值由大到小执行

参考：http://didispace.com/springbootaoplog/
      https://www.zhihu.com/question/32326290
  * */



    /*@Around("webLog()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("111");
    }*/
}
