package com.todo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Pointcut("within(com.todo.service_impl..*)")
    public void logForAllService() {
    }

    @After("logForAllService()")
    public void logForAllMethodCall(JoinPoint jp) {
        logger.log(Level.INFO, "Был вызван сервис метод: " + jp.getSignature().getName());
    }

    @Around("@annotation(LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.log(Level.INFO, "Метод \"" + joinPoint.getSignature() + "\" выполнен за " + executionTime + "мс");
        return proceed;
    }

    @AfterReturning(
            pointcut = "execution(* com.todo.service_impl..*(..)) \"", returning = "result")
    public void logAfterReturning(JoinPoint jp, Object result) {
        logger.log(Level.INFO, "Возвращенное значение метода: \""+jp.getSignature().getName()+"\" -> " + result.toString());
    }
}
