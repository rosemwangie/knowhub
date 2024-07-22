package com.bobo.knowhub.repository;

import com.bobo.knowhub.model.Content;
import com.bobo.knowhub.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ContentRepositoryTest {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    private Users user;
    private Content content;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        user = userRepository.save(user);

        content = new Content();
        content.setTitle("Test Title");
        content.setBody("Test Body");
        content.setUser(user);
        content = contentRepository.save(content);
    }

    @Test
    public void testSaveAndFindContent() {
        Content foundContent = contentRepository.findById(content.getId()).orElse(null);
        assertEquals("Test Title", foundContent.getTitle());
    }

    @Test
    public void testFindAllContent() {
        List<Content> contents = contentRepository.findAll();
        assertEquals(1, contents.size());
        assertEquals("Test Title", contents.get(0).getTitle());
    }
}
