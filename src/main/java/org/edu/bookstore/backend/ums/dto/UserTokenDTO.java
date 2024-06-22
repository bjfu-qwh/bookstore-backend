package org.edu.bookstore.backend.ums.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserTokenDTO implements Serializable {
    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "token")
    private String token;
    @JsonProperty(value = "url")
    private String url;
}
