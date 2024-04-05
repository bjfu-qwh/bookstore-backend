package org.edu.bookstore.backend.business.bms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewBookDTO implements Serializable {

    /**
     * 图书ISBN号码，唯一。
     */
    @JsonProperty
    private String isbn;

    /**
     * 图书名
     */
    @JsonProperty(value = "name")
    private String bookName;

    /**
     * 图书出版社名
     */
    @JsonProperty
    private String press;

    /**
     * 图书价格
     */
    @JsonProperty
    private BigDecimal price;

    /**
     * 图书页数
     */
    @JsonProperty
    private Integer page;

    /**
     * 图书版本描述
     */
    @JsonProperty
    private String edition;

    /**
     * 图书作者的ID列表
     */
    @JsonProperty
    private List<Long> authors;

    @JsonProperty
    private List<String> categories;

    /**
     * 本版图书出版时间
     */
    @JsonProperty
    private LocalDate published;

    /**
     * 图书库存
     */
    @JsonProperty
    private int amount;

    /**
     * 图书装帧
     */
    @JsonProperty
    private String type;

    /**
     * 图书图片信息
     */
    @JsonProperty
    private String url;
}
