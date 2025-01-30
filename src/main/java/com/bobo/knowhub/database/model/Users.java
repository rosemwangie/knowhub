package com.bobo.knowhub.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Entity
@Setter
@Getter
@ToString
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private Long userId;

    private String passwordHash;

    @Column(nullable = false)
    private String role;  // Make sure this field exists

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int tokenBalance;

    private String createdAt;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public Users() {
    }

    // Even though we're using Lombok, let's explicitly add these methods to ensure they exist
    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}