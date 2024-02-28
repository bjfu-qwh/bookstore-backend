package org.edu.bookstore.backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.edu.bookstore.backend.configurationproperties.JWTProperties;
import org.edu.bookstore.backend.dto.ResultDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {
    private final JWTProperties jwtProperties;

    public JWTUtil(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    /**
     * @param userID 用户账号ID
     * @return 有效期1H的JWT
     */
    public String createJWT(String userID) {
        return JWT.create()
                .withSubject(userID)
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpireTime()))
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));
    }

    public ResultDTO<String> parseJWT(String token) {
        return null;
    }
}
