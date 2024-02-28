package org.edu.bookstore.backend.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JWTPropertiesTest {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testJWTConfig() {
        assertInstanceOf(JWTUtil.class, jwtUtil);
        assertNotNull(jwtUtil);
        String testUserID = "randomUser";
        System.out.println(jwtUtil.createJWT(testUserID));
    }
}
