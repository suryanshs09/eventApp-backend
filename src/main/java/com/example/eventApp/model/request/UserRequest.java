package com.example.eventApp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private String contact;
    private String email;
    private LocalDate dob;
}
