package com.example.demo.fortest;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TestComp {

    public TestObj doSomething() {
        Random rand = new Random();
        return new TestObj(rand.nextInt(1000), "test");
    }

}
