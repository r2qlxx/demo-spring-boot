package com.example.demo.infrastructure.repositories;

import com.example.demo.domain.objects.DemoUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Repository Class Test with in-memory db.
@SpringBootTest
class RdsRepositoryImplTest {
    @Autowired
    private RdsRepositoryImpl rds;

    @AfterEach
    void tearDown() {
        rds.delete(1);
    }

    void createUser() {
        rds.create(new DemoUser(1, "demouser1", "demouser1@localhost", "male", LocalDate.parse("1010-01-01")));
    }

    @Test
    void test_create() {
        int expect = 1;
        int actual = rds.create(new DemoUser(1, "demouser1", "demouser1@localhost", "male", LocalDate.parse("1010-01-01")));
        Assertions.assertThat(actual).isEqualTo(expect);
    }

    @Test
    void test_read() {
        createUser();
        Optional<DemoUser> expect = Optional.of(new DemoUser(1, "demouser1", "demouser1@localhost", "male", LocalDate.parse("1010-01-01")));
        Optional<DemoUser> actual = rds.read(1);
        Assertions.assertThat(actual.get()).isEqualTo(expect.get());
    }

    @Test
    void test_readAll() {
        createUser();
        List<DemoUser> expect = new ArrayList<>() {{
            add(new DemoUser(0, "demouser0", "demouser0@localhost", "male", LocalDate.parse("0001-01-01")));
            add(new DemoUser(1, "demouser1", "demouser1@localhost", "male", LocalDate.parse("1010-01-01")));
        }};
        List<DemoUser> actual = rds.readAll();
        Assertions.assertThat(actual).isEqualTo(expect);
    }

    @Test
    void test_update() {
        createUser();
        int expect = 1;
        int actual = rds.update(new DemoUser(1, "demouser2", "demouser2@localhost", "female", LocalDate.parse("2020-02-02")));
        Assertions.assertThat(actual).isEqualTo(expect);
    }

    @Test
    void test_delete() {
        createUser();
        int expect = 1;
        int actual = rds.delete(1);
        Assertions.assertThat(actual).isEqualTo(expect);
    }
}