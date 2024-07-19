package com.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class DemoAspect {

    // 指定したメソッドが実行される前に本メソッド内の処理が実行される。
    @Before("execution(* com.example.demo.domain.services.DemoService.before(..))")
    public void before(JoinPoint jp) {
        log.info("DemoAspect before");
    }

    // 指定したメソッドが実行された後に、指定したメソッドの処理が正常に終了したか、
    // 異常終了したかに関わらず本メソッド内の処理が実行される。
    @After("execution(* com.example.demo.domain.services.DemoService.after(..))")
    public void after(JoinPoint jp) {
        log.info("DemoAspect after");
    }

    // 指定したメソッドが実行された後、指定したメソッドの処理が正常終了した場合に、
    // 本メソッド内の処理が実行される。
    @AfterReturning("execution(* com.example.demo.domain.services.DemoService.afterReturning(..))")
    public void afterReturning(JoinPoint jp) {
        log.info("DemoAspect afterReturning");
    }

    // 指定したメソッドが実行された後、指定したメソッドの処理が異常終了した場合に、
    // 本メソッド内の処理が実行される。
    @AfterThrowing("execution(* com.example.demo.domain.services.DemoService.afterThrowing(..))")
    public void afterThrowing(JoinPoint jp) {
        log.info("DemoAspect afterThrowing");
    }

    // 指定したメソッドが実行される前後に本メソッド内の処理が実行される。
    @Around("execution(* com.example.demo.domain.services.DemoService.around(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        try {
            log.info("DemoAspect around_before");
            result = pjp.proceed();
            log.info("DemoAspect around_after");
        } catch (Throwable e) {
            throw e;
        }

        return result;
    }

    @Around("@annotation(com.example.demo.aop.anotations.ProcTime)")
    public Object procTime(ProceedingJoinPoint pjp) throws Throwable {
        try {
            long start_ms = System.currentTimeMillis();
            Object result = pjp.proceed();
            long procTime_ms = System.currentTimeMillis() - start_ms;
            String className = pjp.getTarget().getClass().getCanonicalName();
            String methodName = pjp.getSignature().getName();
            log.info(className + "." + methodName + "() ended. ProcTime = " + procTime_ms + " ms.");
            return result;
        } catch (Throwable e) {
            throw e;
        }
    }

}