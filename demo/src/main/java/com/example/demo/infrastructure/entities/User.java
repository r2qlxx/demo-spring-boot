package com.example.demo.infrastructure.entities;

import com.example.demo.domain.objects.DemoUser;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
public class User {
    private int userid;
    private String username;
    private String email;
    private String gender;
    private LocalDate birthday;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public DemoUser toDemoUser() {
        return DemoUser.builder().userid(userid).username(username).email(email).gender(gender).birthday(birthday).build();
    }
}
