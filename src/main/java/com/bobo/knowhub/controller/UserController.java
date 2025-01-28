package com.bobo.knowhub.controller;

import com.bobo.knowhub.model.Users;
import com.bobo.knowhub.security.JwtTokenUtil;
import com.bobo.knowhub.service.UserService;
import com.bobo.knowhub.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private JwtTokenUtil jwtTokenUtil;

        /**
         * Register a new user.
         *
         * @param user The user object to be registered.
         * @return ApiResponse with the user details.
         */
        @PostMapping("/register")
        public ApiResponse<Users> registerUser(@RequestBody Users user) {
                Users createdUser = userService.registerUser(user);
                return ApiResponse.success(HttpStatus.CREATED.value(), "User registered successfully", createdUser);
        }

        /**
         * Login user and generate JWT token.
         *
         * @param user The user object containing login credentials.
         * @return ApiResponse with JWT token.
         */
        @PostMapping("/login")
        public ApiResponse<String> loginUser(@RequestBody Users user) {
                String jwtToken = userService.loginUser(user);
                return ApiResponse.success(HttpStatus.OK.value(), "Login successful", jwtToken);
        }

        /**
         * Get user details by ID.
         *
         * @param userId The user ID.
         * @return ApiResponse with user details.
         */
        @GetMapping("/{userId}")
        public ApiResponse<Users> getUser(@PathVariable Long userId) {
                Users user = userService.getUser(userId);
                return ApiResponse.success(HttpStatus.OK.value(), "User retrieved successfully", user);
        }
}
