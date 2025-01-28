package com.bobo.knowhub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {

    private int status;               // HTTP Status Code
    private String message;           // Response message
    private T data;                   // Response data (can be any object)
    private LocalDateTime timestamp;  // Timestamp when response was generated

    // Static method to easily generate a successful response
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(status, message, data, LocalDateTime.now());
    }

    // Static method to easily generate an error response
    public static <T> ApiResponse<T> error(int status, String message) {
        return new ApiResponse<>(status, message, null, LocalDateTime.now());
    }
}
