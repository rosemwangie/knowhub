package com.bobo.knowhub.service;

import com.bobo.knowhub.integration.EthereumIntegration;
import com.bobo.knowhub.model.TokenTransaction;
import com.bobo.knowhub.model.Users;
import com.bobo.knowhub.database.repository.TokenTransactionRepository;
import com.bobo.knowhub.database.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TokenTransactionService {

    @Autowired
    private TokenTransactionRepository tokenTransactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EthereumIntegration ethereumIntegration;

    /**
     * Transfers tokens between users.
     *
     * @param senderId   ID of the sender.
     * @param receiverId ID of the receiver.
     * @param amount     Number of tokens to transfer.
     * @return A success message or error.
     */
    @Transactional
    public String transferTokens(Long senderId, Long receiverId, int amount) {
        Users sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        Users receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (sender.getTokenBalance() < amount) {
            throw new RuntimeException("Insufficient tokens in sender's account");
        }

        // Deduct tokens from sender and add to receiver
        sender.setTokenBalance(sender.getTokenBalance() - amount);
        receiver.setTokenBalance(receiver.getTokenBalance() + amount);
        userRepository.save(sender);
        userRepository.save(receiver);

        // Create a new TokenTransaction with relationships
        TokenTransaction transaction = new TokenTransaction();
        transaction.setSender(sender); // Set sender object instead of senderId
        transaction.setReceiver(receiver); // Set receiver object instead of receiverId
        transaction.setAmount(amount);
        transaction.setCreatedAt(LocalDateTime.now()); // Directly set LocalDateTime
        tokenTransactionRepository.save(transaction);

        // Notify the blockchain about the token transfer
        ethereumIntegration.transferTokens(senderId, receiverId, amount);

        return "Token transfer successful. Transaction ID: " + transaction.getTransactionId();
    }

    /**
     * Retrieves a user's token balance.
     *
     * @param userId ID of the user.
     * @return The user's token balance.
     */
    public int getTokenBalance(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getTokenBalance();
    }
}
