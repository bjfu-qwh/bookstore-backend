package org.edu.bookstore.backend.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.edu.bookstore.backend.business.ums.entity.User;
import org.edu.bookstore.backend.business.ums.mapper.AccountMapper;
import org.edu.bookstore.backend.configurationproperties.JWTProperties;
import org.edu.bookstore.backend.dto.ResultDTO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JWTUtil {
    private final JWTProperties jwtProperties;
    private final AccountMapper accountMapper;

    public JWTUtil(JWTProperties jwtProperties, AccountMapper accountMapper) {
        this.jwtProperties = jwtProperties;
        this.accountMapper = accountMapper;
    }

    /**
     * @param userID 用户账号ID
     * @return 有效期1H的JWT
     */
    public String createJWT(String userID) {
        return JWT.create()
                .withSubject(userID)
                .withIssuer(jwtProperties.getIssuer())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpireTime() * 1000))
                .sign(Algorithm.HMAC256(jwtProperties.getSecret()));
    }

    public ResultDTO<String> parseJWT(String token, String userID, String role) {
        log.info("为账号{}认证", userID);
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(jwtProperties.getSecret()))
                .withIssuer(jwtProperties.getIssuer())
                .build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            String user = jwt.getSubject();
            User userInfo = accountMapper.getByUserID(userID);
            if (user == null || userInfo == null) {
                log.warn("未认证账号ID:{}", userID);
                return ResultDTOUtil.errorUnAuthorized("未认证的用户，请重新登录");
            }
            if (!user.equals(userID)) {
                log.warn("账号不匹配:{} <-> {}", userID, user);
                return ResultDTOUtil.errorForbidden("用户认证信息与当前用户不匹配");
            }
            if (!role.equals(userInfo.getRole())) {
                log.error("账号类型错误:{}", role);
                return ResultDTOUtil.errorForbidden("账号类型错误");
            }
            return null;
        } catch (TokenExpiredException expiredException) {
            return ResultDTOUtil.errorUnAuthorized("登录信息已超时，请重新登录");
        }
    }

}
