package com.example.demo.domain.repositories;

import com.example.demo.domain.objects.DemoUser;

import java.util.List;
import java.util.Optional;

public interface RdsRepository {
    // CRUD.
    int create(DemoUser demoUser);

    Optional<DemoUser> read(int userid);

    List<DemoUser> readAll();

    int update(DemoUser demoUser);

    int delete(int userid);
}
