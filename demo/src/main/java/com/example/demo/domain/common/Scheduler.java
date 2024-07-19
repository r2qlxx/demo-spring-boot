package com.example.demo.domain.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Scheduler {

    //    @Scheduled(initialDelay = 5000, fixedRate = 30000) // ms
    public void schedule() {
        log.info("scheduling.");
    }
}