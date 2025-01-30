package com.bobo.knowhub.service;

import com.bobo.knowhub.integration.EthereumIntegration;
import com.bobo.knowhub.database.model.Users;
import com.bobo.knowhub.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TokenService {

    @Autowired
    private EthereumIntegration ethereumIntegration;

    @Autowired
    private UserRepository userRepository;

    private static final int ARTICLE_REWARD_AMOUNT = 10;
    private static final Long SYSTEM_ACCOUNT_ID = 1L;

    @Transactional
    public void rewardUserForArticle(Users user) {
        try {
            // First check if system account has enough balance
            int systemBalance = ethereumIntegration.getTokenBalance(SYSTEM_ACCOUNT_ID);

            if (systemBalance < ARTICLE_REWARD_AMOUNT) {
                // Log the issue but don't fail the article creation
                System.err.println("Warning: System account has insufficient token balance for rewards");
                return;
            }

            String txHash = ethereumIntegration.transferTokens(
                    SYSTEM_ACCOUNT_ID,
                    user.getId(),
                    ARTICLE_REWARD_AMOUNT
            );

            // Update user's token balance
            int newBalance = ethereumIntegration.getTokenBalance(user.getId());
            user.setTokenBalance(newBalance);
            userRepository.save(user);

            System.out.println("Token reward successful. TxHash: " + txHash + ", New Balance: " + newBalance);
        } catch (Exception e) {
            // Log the error but don't fail the article creation
            System.err.println("Warning: Failed to process token reward: " + e.getMessage());
        }
    }

    public int getUserBalance(Long userId) {
        try {
            return ethereumIntegration.getTokenBalance(userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch token balance: " + e.getMessage(), e);
        }
    }
}