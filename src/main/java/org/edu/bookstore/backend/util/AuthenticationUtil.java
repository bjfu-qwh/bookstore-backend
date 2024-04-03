package org.edu.bookstore.backend.util;

import org.edu.bookstore.backend.dto.JSONResult;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {
    private final JWTUtil jwtUtil;

    public AuthenticationUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public JSONResult<String> checkTokenAndUserRole(String token, String userID, String role) {
        if (token == null || token.isEmpty()) {
            return JSONResultUtil.errorUnAuthorized("用户未认证");
        }
        if (userID != null) {
            return jwtUtil.parseJWT(token, userID, role);
        }
        return null;
    }

    public JSONResult<String> checkTokenOnly(String token) {
        if (token == null || token.isEmpty()) {
            return JSONResultUtil.errorUnAuthorized("用户未认证");
        }
        return jwtUtil.parseJWT(token, null, null);
    }


}
