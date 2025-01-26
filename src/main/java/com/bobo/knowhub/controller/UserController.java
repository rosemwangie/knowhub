package com.bobo.knowhub.controller;


import com.bobo.knowhub.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

        @Autowired
        private UserService userService;

        @Autowired
        private JwtTokenUtil jwtTokenUtil;

        @PostMapping("/register")
        public Users registerUser(@RequestBody Users user) {
            return userService.registerUser(user);
        }

        @PostMapping("/login")
        public String loginUser(@RequestBody Users user) {
            return userService.loginUser(user);
        }

        @GetMapping("/{userId}")
        public Users getUser(@PathVariable Long userId) {
            return userService.getUser(userId);
        }
}
