package com.bobo.knowhub.dto;

import lombok.*;


@Data
@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
}

