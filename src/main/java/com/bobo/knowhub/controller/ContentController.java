package com.bobo.knowhub.controller;

import com.bobo.knowhub.model.Content;
import com.bobo.knowhub.service.ContentService;
import com.bobo.knowhub.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * Uploads new content.
     *
     * @param title       The title of the content.
     * @param description The description of the content.
     * @param file        The file to be uploaded.
     * @param userId      The ID of the user uploading the content.
     * @return ApiResponse with the saved content.
     */
    @PostMapping("/upload")
    public ApiResponse<Content> uploadContent(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId) {
        Content savedContent = contentService.uploadContent(title, description, file, userId);
        return ApiResponse.success(HttpStatus.CREATED.value(), "Content uploaded successfully", savedContent);
    }

    /**
     * Retrieves content by its ID.
     *
     * @param contentId The ID of the content.
     * @return ApiResponse with the content details.
     */
    @GetMapping("/{contentId}")
    public ApiResponse<Content> getContent(@PathVariable Long contentId) {
        Content content = contentService.getContent(contentId);
        return ApiResponse.success(HttpStatus.OK.value(), "Content retrieved successfully", content);
    }

    /**
     * Lists all content.
     *
     * @return ApiResponse with a list of all content.
     */
    @GetMapping("/")
    public ApiResponse<List<Content>> listContent() {
        List<Content> contentList = contentService.listContent();
        return ApiResponse.success(HttpStatus.OK.value(), "Content list retrieved successfully", contentList);
    }
}
