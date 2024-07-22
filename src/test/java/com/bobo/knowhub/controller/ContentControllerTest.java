package com.bobo.knowhub.controller;

import com.bobo.knowhub.model.Content;
import com.bobo.knowhub.model.Users;
import com.bobo.knowhub.service.ContentServiceImpl;
import com.bobo.knowhub.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContentController.class)
public class ContentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentServiceImpl contentService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    private Users user;
    private Content content;

    @BeforeEach
    void setUp() {
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
    @WithMockUser(username = "testuser")
    public void testCreateContent() throws Exception {
        Mockito.when(userDetailsService.loadUserByUsername(anyString())).thenReturn((UserDetails) user);
        Mockito.when(contentService.saveContent(any(Content.class))).thenReturn(content);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/content/create")
                        .contentType("application/json")
                        .content("{\"title\":\"Test Title\",\"body\":\"Test Body\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body").value("Test Body"));
    }

    @Test
    @WithMockUser(username = "testuser")
    public void testGetAllContent() throws Exception {
        Mockito.when(contentService.getAllContent()).thenReturn(List.of(content));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/content/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].body").value("Test Body"));
    }
}
