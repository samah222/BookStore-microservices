package com.samah.orderservice.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class CentralLogging {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.samah.orderservice.*.*(..))")
    private void publicMethodsFromLoggingPackage() {
    }

    @Around(value = "publicMethodsFromLoggingPackage()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();

        logger.debug(">> {}() - {}", methodName, Arrays.toString(args));
        Object result = joinPoint.proceed();
        logger.debug("<< {}() - {}", methodName, result);
        return result;
    }

    // @Before("execution(* com.samah.orderservice..*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();

        logger.debug(">> {}() - {}", methodName, Arrays.toString(args));
        logger.info("Method execution started: {}", joinPoint.getSignature());
    }

    // @After("execution(* com.samah.orderservice..*.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Method execution completed: {}", joinPoint.getSignature());
        String methodName = joinPoint.getSignature().getName();
        // Object result = joinPoint.proceed();
        logger.debug("<< {}() - {}", methodName, "result");
    }
}
