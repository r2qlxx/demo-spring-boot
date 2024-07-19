package com.example.demo.fortest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestComp tc;

    public TestObj doService() {
        TestObj to = tc.doSomething();
        int squared = calc2Squared(to.getId());
        to.setId(squared + 1);
        return to;
    }

    public int calc2Squared(int base) {
        return base * base;
    }
}
