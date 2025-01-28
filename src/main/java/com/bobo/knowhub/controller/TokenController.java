package com.bobo.knowhub.controller;

import com.bobo.knowhub.dto.TokenTransferRequest;
import com.bobo.knowhub.service.TokenTransactionService;
import com.bobo.knowhub.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    @Autowired
    private TokenTransactionService tokenService;

    /**
     * Transfers tokens between users.
     *
     * @param request The token transfer request object containing sender, receiver, and amount.
     * @return ApiResponse with transaction status.
     */
    @PostMapping("/transfer")
    public ApiResponse<String> transferTokens(@RequestBody TokenTransferRequest request) {
        String result = tokenService.transferTokens(request.getSenderId(), request.getReceiverId(), request.getAmount());
        return ApiResponse.success(HttpStatus.OK.value(), "Token transfer successful", result);
    }

    /**
     * Retrieves the token balance of a user.
     *
     * @param userId The ID of the user.
     * @return ApiResponse with the token balance.
     */
    @GetMapping("/balance/{userId}")
    public ApiResponse<Integer> getTokenBalance(@PathVariable Long userId) {
        int balance = tokenService.getTokenBalance(userId);
        return ApiResponse.success(HttpStatus.OK.value(), "Token balance retrieved successfully", balance);
    }
}
