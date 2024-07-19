package com.example.demo.domain.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class DemoUser {
    private int userid;
    private String username;
    private String email;
    private String gender;
    private LocalDate birthday;
}
