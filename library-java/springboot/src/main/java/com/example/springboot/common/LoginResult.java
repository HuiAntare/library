package com.example.springboot.common;

import lombok.Data;

@Data
public class LoginResult {
    private Integer id;
    private String username;
    private String phone;
    private String email;
}
