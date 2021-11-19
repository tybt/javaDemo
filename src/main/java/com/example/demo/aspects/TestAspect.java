package com.example.demo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAspect {
    @Around("@annotation(com.example.demo.annotations.TestAnonations)")
    public Object testFirst(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object data= proceedingJoinPoint.getArgs();
        System.out.println("哈哈哈哈 被执行了");
        return proceedingJoinPoint.proceed();

    }
}
