package com.example.demo.domain.services;

import com.example.demo.domain.common.*;
import com.example.demo.domain.exceptions.SystemException;
import com.example.demo.domain.objects.CacheKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemoService {
    private final Cacher cacher;
    private final Retryer retryer;
    private final Asyncer asyncer;
    private final HttpClient_ http;

    // -------------------
    // -- Just methods. --
    // -------------------
    public String doSomething() {
        // snip
        return "demo";
    }

    public String doSomething(int id) {
        // snip
        return "demo";
    }


    // -----------------
    // -- Log sample. --
    // -----------------
    public void appLog() {
        log.info(AppLogger.readAppLogMsg("APP_LOG_001", "arg0"));
        log.info(AppLogger.readAppLogMsg("APP_ERROR_LOG_001", "arg0"));
    }


    // -----------------
    // -- AOP sample. --
    // -----------------
    public void before() {
        log.info("before");
    }

    public void after() {
        log.info("after");
    }

    public void afterReturning() {
        log.info("afterReturning");
    }

    public void afterThrowing() {
        log.info("afterThrowing");
        throw new RuntimeException();
    }

    public void around() {
        log.info("around");
    }
    // 2024-07-18T13:33:20.378+09:00  INFO 23604 --- [demo] [main] com.example.demo.aop.DemoAspect          : DemoAspect before
    // 2024-07-18T13:33:20.378+09:00  INFO 23604 --- [demo] [main] c.e.demo.domain.services.DemoService     : before

    // 2024-07-18T13:33:20.378+09:00  INFO 23604 --- [demo] [main] c.e.demo.domain.services.DemoService     : after
    // 2024-07-18T13:33:20.379+09:00  INFO 23604 --- [demo] [main] com.example.demo.aop.DemoAspect          : DemoAspect after

    // 2024-07-18T13:33:20.379+09:00  INFO 23604 --- [demo] [main] c.e.demo.domain.services.DemoService     : afterReturning
    // 2024-07-18T13:33:20.379+09:00  INFO 23604 --- [demo] [main] com.example.demo.aop.DemoAspect          : DemoAspect afterReturning

    // 2024-07-18T13:33:20.379+09:00  INFO 23604 --- [demo] [main] c.e.demo.domain.services.DemoService     : afterThrowing
    // 2024-07-18T13:33:20.379+09:00  INFO 23604 --- [demo] [main] com.example.demo.aop.DemoAspect          : DemoAspect afterThrowing

    // 2024-07-18T13:33:20.379+09:00  INFO 23604 --- [demo] [main] com.example.demo.aop.DemoAspect          : DemoAspect around_before
    // 2024-07-18T13:33:20.379+09:00  INFO 23604 --- [demo] [main] c.e.demo.domain.services.DemoService     : around
    // 2024-07-18T13:33:20.379+09:00  INFO 23604 --- [demo] [main] com.example.demo.aop.DemoAspect          : DemoAspect around_after


    // -------------------
    // -- Cache sample. --
    // -------------------
    public void cache() {
        log.info(cacher.cache(new CacheKey("key")).toString());
        log.info(cacher.cache(new CacheKey("key2")).toString());
        log.info(cacher.cache(new CacheKey("key")).toString());
    }
    // 2024-07-18T14:10:25.831+09:00  INFO 25416 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Cacher    : heavy task started.
    // 2024-07-18T14:10:30.834+09:00  INFO 25416 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Cacher    : heavy task ended.
    // 2024-07-18T14:10:30.836+09:00  INFO 25416 --- [demo] [nio-8080-exec-1] com.example.demo.aop.DemoAspect          : com.example.demo.domain.common.Cacher.cache() ended. ProcTime = 5003 ms.
    // 2024-07-18T14:10:30.838+09:00  INFO 25416 --- [demo] [nio-8080-exec-1] c.e.demo.domain.services.DemoService     : CacheValue(value=value)
    // 2024-07-18T14:10:30.838+09:00  INFO 25416 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Cacher    : heavy task started.
    // 2024-07-18T14:10:35.845+09:00  INFO 25416 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Cacher    : heavy task ended.
    // 2024-07-18T14:10:35.845+09:00  INFO 25416 --- [demo] [nio-8080-exec-1] com.example.demo.aop.DemoAspect          : com.example.demo.domain.common.Cacher.cache() ended. ProcTime = 5007 ms.
    // 2024-07-18T14:10:35.845+09:00  INFO 25416 --- [demo] [nio-8080-exec-1] c.e.demo.domain.services.DemoService     : CacheValue(value=value)
    // 2024-07-18T14:10:35.846+09:00  INFO 25416 --- [demo] [nio-8080-exec-1] c.e.demo.domain.services.DemoService     : CacheValue(value=value)


    // -----------
    // -- Retry --
    // -----------
    public void retry() {
        try {
            retryer.retry();
        } catch (Exception e) {
            // snip
        }
    }
    // 2024-07-18T14:36:00.695+09:00  INFO 24140 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Retryer   : retry
    // 2024-07-18T14:36:01.702+09:00  INFO 24140 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Retryer   : retry
    // 2024-07-18T14:36:02.709+09:00  INFO 24140 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Retryer   : retry
    // 2024-07-18T14:36:02.710+09:00  INFO 24140 --- [demo] [nio-8080-exec-1] com.example.demo.domain.common.Retryer   : recover


    // -----------
    // -- Async --
    // -----------
    public void async() {
        log.info("async 1");
        CompletableFuture<String> result = asyncer.async();
        log.info("async 2");
        try {
            log.info(result.get());
        } catch (Exception e) {
            // snip
        }
    }
    // 2024-07-18T14:44:01.334+09:00  INFO 4392 --- [demo] [nio-8080-exec-1] c.e.demo.domain.services.DemoService     : async 1
    // 2024-07-18T14:44:01.339+09:00  INFO 4392 --- [demo] [nio-8080-exec-1] c.e.demo.domain.services.DemoService     : async 2
    // 2024-07-18T14:44:01.340+09:00  INFO 4392 --- [demo] [         task-1] com.example.demo.domain.common.Asyncer   : heavy task before
    // 2024-07-18T14:44:06.352+09:00  INFO 4392 --- [demo] [         task-1] com.example.demo.domain.common.Asyncer   : heavy task after
    // 2024-07-18T14:44:06.352+09:00  INFO 4392 --- [demo] [nio-8080-exec-1] c.e.demo.domain.services.DemoService     : Async.


    // -----------
    // -- Http --
    // -----------
    public void http() {
        String post = http.fetchPost();
        log.info(post);

        String result = http.createPost();
        log.info(result);
    }


    // ---------------------
    // -- Error Handling. --
    // ---------------------
    public void handleErr() {
        try {
            int a = 1 / 0;
        } catch (ArithmeticException e) {
            throw new SystemException(e, "APP_ERROR_LOG_002", new Object[]{"arg0", "arg1"});
        }
    }
}
