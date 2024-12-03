package edu.hrbu.trace_backend_business.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//  接口访问流量控制注解
public @interface TrafficLimit {

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    int punish() default 5;

    int times() default 1;

    int maxRequest() default 5;

}
