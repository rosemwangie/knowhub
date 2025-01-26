package com.bobo.knowhub.service;


import com.bobo.knowhub.model.Content;
import com.bobo.knowhub.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private IPFSIntegration ipfsIntegration;

    public Content uploadContent(Content content) {
        String fileHash = ipfsIntegration.uploadFile(content.getFile());
        content.setFileHash(fileHash);
        return contentRepository.save(content);
    }

    public Content getContent(Long contentId) {
        return contentRepository.findById(contentId).orElseThrow(() -> new RuntimeException("Content not found"));
    }

    public List<Content> listContent() {
        return contentRepository.findAll();
    }
}
