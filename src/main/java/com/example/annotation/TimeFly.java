package com.example.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TimeFly {
    String type() default "";
}
