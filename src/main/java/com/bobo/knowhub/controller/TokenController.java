package com.bobo.knowhub.controller;

import com.bobo.knowhub.service.TokenService;
import com.bobo.knowhub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/store/tokens")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping("/balance")
    public ResponseEntity<?> getMyBalance(Principal principal) {
        try {
            var user = userService.getUserByUsername(principal.getName());
            int balance = tokenService.getUserBalance(user.getId());
            return ResponseEntity.ok(new TokenBalanceResponse(balance));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

class TokenBalanceResponse {
    private int balance;

    public TokenBalanceResponse(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}