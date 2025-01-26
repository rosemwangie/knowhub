package com.bobo.knowhub.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/transfer")
    public String transferTokens(@RequestBody String senderId, @RequestBody String receiverId, @RequestBody String amount) {
        return tokenService.transferTokens(senderId, receiverId, amount);
    }

    @GetMapping("/balance/{userId}")
    public int getTokenBalance(@PathVariable Long userId) {
        return tokenService.getTokenBalance(userId);
    }
}
