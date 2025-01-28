package com.bobo.knowhub.service;

import com.bobo.knowhub.model.Users;
import com.bobo.knowhub.database.repository.UserRepository;
import com.bobo.knowhub.security.JwtTokenUtil;
import com.bobo.knowhub.exception.UserNotFoundException;
import com.bobo.knowhub.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Registers a new user.
     *
     * @param user The user object to be registered.
     * @return The registered user object.
     */
    public Users registerUser(Users user) {
        System.out.println("Received registration request for user: " + user.getEmail());
        // Hash the password before saving
        user.setPasswordHash(hashPassword(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param user The user object containing login credentials.
     * @return A JWT token if credentials are valid.
     */
    public String loginUser(Users user) {
        // Find user by email
        Users existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with email: " + user.getEmail());
        }

        // Verify password using BCrypt
        if (verifyPassword(user.getPassword(), existingUser.getPasswordHash())) {
            // Generate and return JWT token
            return jwtTokenUtil.generateToken(existingUser);
        } else {
            throw new InvalidCredentialsException("Invalid credentials for email: " + user.getEmail());
        }
    }


    /**
     * Retrieves a user by their ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user object.
     */
    public Users getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    /**
     * Hashes a plaintext password using BCrypt.
     *
     * @param password The plaintext password.
     * @return The hashed password.
     */
    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Verifies a plaintext password against a hashed password.
     *
     * @param rawPassword     The plaintext password.
     * @param hashedPassword  The hashed password.
     * @return True if the passwords match, false otherwise.
     */
    private boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
