package org.edu.bookstore.backend.business.bms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookVO implements Serializable {
    @JsonProperty(value = "id")
    private long id;

    @JsonProperty(value = "bookName")
    private String bookName;

    @JsonProperty(value = "pressName")
    private String pressName;

    @JsonProperty(value = "price")
    private BigDecimal price;

    /**
     * 图书的默认图片
     */
    @JsonProperty(value = "url")
    private String url;

}
