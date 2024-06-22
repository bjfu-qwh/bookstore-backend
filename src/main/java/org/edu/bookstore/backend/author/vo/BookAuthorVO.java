package org.edu.bookstore.backend.author.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用于图书列表展示作者相关信息
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookAuthorVO implements Serializable {
    @JsonProperty
    private Long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String nation;
}
