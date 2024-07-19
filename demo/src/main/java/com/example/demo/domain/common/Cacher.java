package com.example.demo.domain.common;

import com.example.demo.aop.anotations.ProcTime;
import com.example.demo.domain.objects.CacheKey;
import com.example.demo.domain.objects.CacheValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Cacher {
    @Cacheable("name")
    @ProcTime
    public CacheValue cache(CacheKey cacheKey) {
        heavyTask();
        return new CacheValue("value");
    }

    private void heavyTask() {
        try {
            log.info("heavy task started.");
            Thread.sleep(5000);
            log.info("heavy task ended.");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
