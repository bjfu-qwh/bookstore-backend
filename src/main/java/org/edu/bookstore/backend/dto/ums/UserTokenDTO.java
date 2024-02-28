package org.edu.bookstore.backend.dto.ums;

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
    String username;
    String email;
    String token;
}
