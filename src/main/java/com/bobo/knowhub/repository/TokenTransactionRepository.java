package com.bobo.knowhub.repository;

import com.bobo.knowhub.model.TokenTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenTransactionRepository extends JpaRepository<TokenTransaction, Long> {
}
