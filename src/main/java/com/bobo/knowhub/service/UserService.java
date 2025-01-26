package com.bobo.knowhub.service;

import com.bobo.knowhub.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public Users registerUser(Users user) {
        user.setPasswordHash(hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public String loginUser(Users user) {
        Users existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null && verifyPassword(user.getPassword(), existingUser.getPasswordHash())) {
            return jwtTokenUtil.generateToken(existingUser);
        }
        throw new RuntimeException("Invalid credentials");
    }

    public Users getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    private String hashPassword(String password) {
        // Implement password hashing logic
    }

    private boolean verifyPassword(String rawPassword, String hashedPassword) {
        // Implement password verification logic
    }
}
