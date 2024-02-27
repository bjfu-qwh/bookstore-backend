package org.edu.bookstore.backend.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JWTPropertiesTest {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testJWTConfig() {
        System.out.println(jwtUtil.createJWT("114514"));
    }
}
