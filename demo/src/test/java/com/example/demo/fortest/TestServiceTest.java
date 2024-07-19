package com.example.demo.fortest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

class TestServiceTest {
    @Mock
    private TestComp tc;

    @Spy
    @InjectMocks
    private TestService ts;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("TestService test 1")
    void test_1() {
        TestObj expect = new TestObj(10001, "test");
        doReturn(new TestObj(100, "test")).when(tc).doSomething();
        TestObj actual = ts.doService();
        assertThat(actual).isEqualTo(expect);
    }

    @Test
    @DisplayName("TestService test 2")
    void test_2() {
        TestObj expect = new TestObj(10001, "test");
        doReturn(new TestObj(100, "test")).when(tc).doSomething();
        TestObj actual = ts.doService();
        assertThat(actual).isEqualTo(expect);
    }
}