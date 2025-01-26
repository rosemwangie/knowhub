package com.bobo.knowhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/verification")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    @PostMapping("/request")
    public String requestVerification(@RequestBody String contentId) {
        return verificationService.requestVerification(contentId);
    }

    @PostMapping("/approve")
    public String approveVerification(@RequestBody String verificationId) {
        return verificationService.approveVerification(verificationId);
    }
}
