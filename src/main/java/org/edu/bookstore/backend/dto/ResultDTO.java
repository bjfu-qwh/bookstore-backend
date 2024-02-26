package org.edu.bookstore.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultDTO<T> {
    /**
     * 返回的状态码，简化起见可以直接模拟http
     */
    @JsonProperty(value = "code")
    private int code;

    /**
     * 传给用户的消息。一是要给出足够的提示，二是不能有敏感信息
     */
    @JsonProperty(value = "message")
    private String message;

    @JsonProperty(value = "data")
    private T data;
}
