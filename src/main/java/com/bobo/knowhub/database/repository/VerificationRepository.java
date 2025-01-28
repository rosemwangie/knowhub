package com.bobo.knowhub.database.repository;

import com.bobo.knowhub.database.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRepository extends JpaRepository<Verification, Long> {
    // Find verifications for a specific content
    List<Verification> findByContentId(Long contentId);

    // Find verifications made by a specific user (verifier)
    List<Verification> findByVerifierId(Long verifierId);

    // Find verifications by status (Pending, Approved, Rejected)
    List<Verification> findByStatus(String status);
}
