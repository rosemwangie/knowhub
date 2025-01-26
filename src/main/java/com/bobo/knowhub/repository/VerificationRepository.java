package com.bobo.knowhub.repository;

import com.bobo.knowhub.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<Verification, Long> {
}