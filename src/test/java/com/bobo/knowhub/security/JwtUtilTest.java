package com.bobo.knowhub.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    public void testGenerateToken() {
        String token = jwtUtil.generateToken("testUser");
        assertNotNull(token);
    }

    @Test
    public void testExtractUsername() {
        String token = jwtUtil.generateToken("testUser");
        String username = jwtUtil.extractUsername(token);
        assertEquals("testUser", username);
    }

    @Test
    public void testExtractExpiration() {
        String token = jwtUtil.generateToken("testUser");
        Date expiration = jwtUtil.extractExpiration(token);
        assertTrue(expiration.after(new Date()));
    }

    @Test
    public void testValidateToken() {
        String token = jwtUtil.generateToken("testUser");
        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    public void testExtractJwtFromRequest() {
        String bearerToken = "Bearer " + jwtUtil.generateToken("testUser");
        HttpServletRequest request = new MockHttpServletRequest() {{
            addHeader("Authorization", bearerToken);
        }};
        String extractedToken = jwtUtil.extractJwtFromRequest(request);
        assertEquals(bearerToken.substring(7), extractedToken);
    }
}
