package com.bobo.knowhub.dto;

import lombok.Data;

@Data
public class ArticleRequest {
    private String title;
    private String content;
//    private String ipfsHash;  // Add IPFS hash here
}
