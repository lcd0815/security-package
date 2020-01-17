package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * lcd  2020/1/4
 * Description:
 */
//@Aspect
//@Component
public class TimeAspect {
    @Around(value = "execution (* com.imooc.web.controller..*.*(..))")
    public Object timeAspect(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("com/imooc/web/aspect/TimeAspect");
        long start = System.currentTimeMillis();
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("TimeAspect"+arg);
        }
        Object proceed = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println("TimeAspect 耗时"+(end-start));
        return proceed;
    }
}
