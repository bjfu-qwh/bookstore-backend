package org.edu.bookstore.backend.business.author.dto;

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
public class AuthorSelectItemDTO implements Serializable {
    @JsonProperty
    private long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String nation;
}
