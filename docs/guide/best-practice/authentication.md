# 基于JWT的认证模块设计

本文描述在线图书销售系统如何基于[JWT](https://jwt.io/)实现简单的认证功能。<br>
JWT是一种**无状态且支持跨域**的协议。其能减轻服务端压力且实现足够轻量化，因而决定使用JWT。

## 认证功能概述

认证子系统包含下列功能：

- JWT**创建**：根据**用户账号ID生成有效期为一小时的token**。
- JWT**检验**：用户访问**需要保护的资源**后，系统根据**客户端传送的token以及账号ID**等相关信息，认证用户是否有足够权限。

## 认证功能设计与实现

### 依赖管理

项目基于[java-jwt](https://github.com/auth0/java-jwt)设计认证功能。为使用，在pom.xml中写入

```xml

<dependency>
    <groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>${java.jwt}</version> <!--4.4.0-->
</dependency>
```

### 配置文件描述

项目为**JWT自定义配置**。首先，激活配置类**JWTProperties**。

```java
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
```

你可以在名为**configurationproperties**的包内找到这个类，JWT有关的配置有三项：

- secret：JWT使用HMAC256算法加密时，需要使用密钥。
- 过期时间：统一设计为**1小时**。
- 签发方

同时，项目**单独为JWT使用配置文件**，在**src/main/resource**目录下，创建**application-jwt.yaml**文件，
之后按说明添加配置：

```yaml
jwt:
  expire-time: 60
  secret: "your-secret-key"
  issuer: "your-issuer"
```

需要指定**secret和issuer**两个选项。之后可以在配置类中读取到相关配置的值。

### JWT Token的生成与校验

[JWTUtil类](../../../src/main/java/org/edu/bookstore/backend/util/JWTUtil.java)负责实现JWT相关功能。<br>
由于这个类已经被Spring容器管理，可以通过@Autowired等各种注入手段使用方法。