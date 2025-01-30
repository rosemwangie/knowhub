package com.bobo.knowhub.dto;

import lombok.*;

@Data
@Getter
@Setter
public class AuthResponse {
    private String token;
    private String username;
    private String role;
}
