package com.bobo.knowhub.config;

import io.ipfs.api.IPFS;
import io.ipfs.multiaddr.MultiAddress;  // Corrected import
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IPFSConfig {

    @Bean
    public IPFS ipfs() {
        // Connect to your local IPFS daemon
        return new IPFS(new MultiAddress("/ip4/127.0.0.1/tcp/5001"));
    }
}