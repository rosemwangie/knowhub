package com.bobo.knowhub.controller;

import com.bobo.knowhub.model.Content;
import com.bobo.knowhub.model.Users;
import com.bobo.knowhub.service.ContentServiceImpl;
import com.bobo.knowhub.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/upload")
    public Content uploadContent(@RequestBody Content content) {
        return contentService.uploadContent(content);
    }

    @GetMapping("/{contentId}")
    public Content getContent(@PathVariable Long contentId) {
        return contentService.getContent(contentId);
    }

    @GetMapping("/")
    public List<Content> listContent() {
        return contentService.listContent();
    }
}
