package org.edu.bookstore.backend.util;

import org.edu.bookstore.backend.constant.code.Code;
import org.edu.bookstore.backend.dto.JSONResult;
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

    @Test
    public void testJWTParser() {
        String userID = "testUserID";
        String token = jwtUtil.createJWT(userID);
        JSONResult<String> parsedResult = jwtUtil.parseJWT(token, userID, "user");
        assertEquals(parsedResult.getCode(), Code.CODE_SUCCESS);
        String wrongUserID = "wrong";
        String wrongToken = jwtUtil.createJWT(wrongUserID);
        JSONResult<String> wrongResult = jwtUtil.parseJWT(wrongToken, userID, "user");
        assertEquals(wrongResult.getCode(), Code.CODE_FORBIDDEN);
    }
}
