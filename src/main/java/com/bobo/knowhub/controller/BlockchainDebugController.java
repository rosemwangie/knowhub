package com.bobo.knowhub.controller;

import com.bobo.knowhub.integration.EthereumIntegration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/debug/blockchain")
public class BlockchainDebugController {

    @Autowired
    private EthereumIntegration ethereumIntegration;

    @GetMapping("/status")
    public ResponseEntity<?> getBlockchainStatus() {
        return ResponseEntity.ok(ethereumIntegration.getDebugInfo());
    }
}