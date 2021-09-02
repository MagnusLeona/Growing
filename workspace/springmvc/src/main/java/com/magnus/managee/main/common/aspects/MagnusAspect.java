package com.magnus.managee.main.common.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MagnusAspect {

    @Before("point()")
    public void before() {
        System.out.println("Before method invoked");
    }

    @Pointcut("execution(* com.magnus.managee.main.work.services.AService.*(..))")
    public void point(){};
}
