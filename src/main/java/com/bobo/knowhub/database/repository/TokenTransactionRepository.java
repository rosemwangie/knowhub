package com.bobo.knowhub.database.repository;

import com.bobo.knowhub.database.model.TokenTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenTransactionRepository extends JpaRepository<TokenTransaction, Long> {
    // Find all transactions sent by a particular user
    List<TokenTransaction> findBySenderId(Long senderId);

    // Find all transactions received by a particular user
    List<TokenTransaction> findByReceiverId(Long receiverId);
}
