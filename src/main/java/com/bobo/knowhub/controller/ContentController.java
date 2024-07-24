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
        // Get the authenticated user's username from the security context
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Load the user's details using the custom UserDetailsService
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);

        // Get the actual User object from the custom UserDetails implementation
        Users user = userDetails.getUser();

        // Set the user for the content
        content.setUser(user);

        // Save the content
        return contentService.saveContent(content);
    }

    @GetMapping("/all")
    public List<Content> getAllContent() {
        // Return all content
        return contentService.getAllContent();
    }
}
