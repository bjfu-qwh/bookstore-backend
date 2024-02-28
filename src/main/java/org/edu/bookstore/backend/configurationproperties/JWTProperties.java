package org.edu.bookstore.backend.configurationproperties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * JWT相关配置项，如密钥、过期时间和签发方等。
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:application-jwt.yaml")
public class JWTProperties {
    /**
     * JWT使用的密钥
     */
    private String secret;
    /**
     * JWT的过期时间
     */
    private long expireTime;
    /**
     * JWT签发方
     */
    private String issuer;
}
