package edu.hrbu.trace_backend_business.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//  接口防止重复提交注解
public @interface AntiResubmit {

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    int punish() default 1;

    int times() default 1;

}
