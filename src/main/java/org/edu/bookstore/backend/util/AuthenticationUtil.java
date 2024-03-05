package org.edu.bookstore.backend.util;

import org.edu.bookstore.backend.dto.ResultDTO;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUtil {
    private final JWTUtil jwtUtil;

    public AuthenticationUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public ResultDTO<String> checkToken(String token, String userID, String role) {
        if (token == null || token.isEmpty()) {
            return ResultDTOUtil.errorUnAuthorized("用户未认证");
        }
        if (userID != null) {
            return jwtUtil.parseJWT(token, userID, role);
        }
        return null;
    }


}
