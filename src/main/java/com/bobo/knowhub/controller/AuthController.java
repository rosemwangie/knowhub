package com.bobo.knowhub.controller;

import com.bobo.knowhub.model.Users;
import com.bobo.knowhub.repository.UserRepository;
import com.bobo.knowhub.security.JwtUtil;
import com.bobo.knowhub.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String registerUser(@RequestBody Users user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already in use";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setReputation(0); // initialize reputation
        userRepository.save  (user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestBody Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername());
    }
}