package com.bobo.knowhub.controller;

import com.bobo.knowhub.integration.EthereumIntegration;
import com.bobo.knowhub.service.TokenService;
import com.bobo.knowhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/debug/tokens")  // Changed from /store to /api
public class TokenDebugController {

    @Autowired
    private EthereumIntegration ethereumIntegration;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping("/status")  // Simplified endpoint
    public ResponseEntity<?> getSystemStatus() {
        try {
            Map<String, Object> status = new HashMap<>();

            // Check system account balance
            int systemBalance = ethereumIntegration.getTokenBalance(1L);
            status.put("systemAccountBalance", systemBalance);

            // Get contract address
            status.put("contractAddress", ethereumIntegration.getContractAddress());

            return ResponseEntity.ok(status);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Error checking system status: " + e.getMessage()));
        }
    }

    @PostMapping("/reward/{username}")  // Simplified endpoint
    public ResponseEntity<?> manualReward(@PathVariable String username) {
        try {
            var user = userService.getUserByUsername(username);
            Map<String, Object> result = new HashMap<>();

            // Get balance before
            int balanceBefore = ethereumIntegration.getTokenBalance(user.getId());
            result.put("balanceBefore", balanceBefore);

            // Attempt reward
            String txHash = ethereumIntegration.transferTokens(1L, user.getId(), 10);
            result.put("transactionHash", txHash);

            // Get balance after
            int balanceAfter = ethereumIntegration.getTokenBalance(user.getId());
            result.put("balanceAfter", balanceAfter);

            // Update user balance in database
            user.setTokenBalance(balanceAfter);
            userService.updateUser(user);

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Error processing reward: " + e.getMessage()));
        }
    }
}