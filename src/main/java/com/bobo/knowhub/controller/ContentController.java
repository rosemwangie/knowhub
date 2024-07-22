package com.bobo.knowhub.controller;

import com.bobo.knowhub.model.Content;
import com.bobo.knowhub.model.CustomUserDetails;
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
    private ContentServiceImpl contentService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/create")
    public Content createContent(@RequestBody Content content) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        Users user = userDetails.getUser();
        content.setUser(user);
        return contentService.saveContent(content);
    }

    @GetMapping("/all")
    public List<Content> getAllContent() {
        return contentService.getAllContent();
    }
}
