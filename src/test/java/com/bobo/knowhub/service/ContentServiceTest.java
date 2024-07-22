package com.bobo.knowhub.service;

import com.bobo.knowhub.model.Content;
import com.bobo.knowhub.model.Users;
import com.bobo.knowhub.repository.ContentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ContentServiceTest {

    @InjectMocks
    private ContentServiceImpl contentService;

    @Mock
    private ContentRepository contentRepository;

    private Users user;
    private Content content;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new Users();
        user.setId(1L);
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");

        content = new Content();
        content.setId(1L);
        content.setTitle("Test Title");
        content.setBody("Test Body");
        content.setUser(user);
    }

    @Test
    public void testSaveContent() {
        when(contentRepository.save(any(Content.class))).thenReturn(content);

        Content savedContent = contentService.saveContent(content);
        assertEquals("Test Title", savedContent.getTitle());
    }

    @Test
    public void testGetAllContent() {
        when(contentRepository.findAll()).thenReturn(List.of(content));

        List<Content> contents = contentService.getAllContent();
        assertEquals(1, contents.size());
        assertEquals("Test Title", contents.get(0).getTitle());
    }
}
