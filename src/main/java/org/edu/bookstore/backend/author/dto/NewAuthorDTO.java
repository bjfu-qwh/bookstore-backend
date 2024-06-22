package org.edu.bookstore.backend.author.dto;

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
public class NewAuthorDTO implements Serializable {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "nation")
    private String nation;

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "brief")
    private String brief;
}
