package org.edu.bookstore.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
@PropertySource("classpath:application-jwt.yaml")
public class JWTProperties {
    private String secret;
    private long expireTime;
}
