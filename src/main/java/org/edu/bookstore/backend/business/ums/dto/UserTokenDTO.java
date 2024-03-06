package org.edu.bookstore.backend.business.ums.dto;

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
    @JsonProperty(value = "username")
    String username;
    @JsonProperty(value = "email")
    String email;
    @JsonProperty(value = "token")
    String token;
}
