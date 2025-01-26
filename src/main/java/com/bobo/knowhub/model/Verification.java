package com.bobo.knowhub.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long verificationId;
    private Long contentId;
    private Long verifierId;
    private String status; // Pending, Approved, Rejected
    private String verifiedAt;
}
