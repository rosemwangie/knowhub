package com.bobo.knowhub.dto;

import lombok.*;

@Data
@Getter
@Setter
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}
