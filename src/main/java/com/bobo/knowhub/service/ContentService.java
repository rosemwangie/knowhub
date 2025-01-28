package com.bobo.knowhub.service;

import com.bobo.knowhub.integration.IPFSIntegration;
import com.bobo.knowhub.model.Content;
import com.bobo.knowhub.model.Users;
import com.bobo.knowhub.database.repository.ContentRepository;
import com.bobo.knowhub.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private IPFSIntegration ipfsIntegration;

    @Autowired
    private UserRepository userRepository; // To associate user with content

    public Content uploadContent(String title, String description, MultipartFile multipartFile, Long userId) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new RuntimeException("File is required");
        }

        try {
            // Fetch the user to associate with content
            Users user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Save MultipartFile to a temporary file
            File tempFile = File.createTempFile("upload-", multipartFile.getOriginalFilename());
            multipartFile.transferTo(tempFile);

            // Upload the temporary file to IPFS
            String fileHash = ipfsIntegration.uploadFile(tempFile);

            // Delete the temporary file after uploading
            tempFile.delete();

            // Create and save content metadata
            Content content = new Content();
            content.setTitle(title);
            content.setDescription(description);
            content.setFileHash(fileHash);
            content.setUser(user);  // Set the user who uploaded the content
            content.setCreatedAt(LocalDateTime.now().toString());
            return contentRepository.save(content);

        } catch (IOException e) {
            throw new RuntimeException("Error processing file: " + e.getMessage(), e);
        }
    }

    public Content getContent(Long contentId) {
        return contentRepository.findById(contentId)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

    public List<Content> listContent() {
        return contentRepository.findAll();
    }
}
