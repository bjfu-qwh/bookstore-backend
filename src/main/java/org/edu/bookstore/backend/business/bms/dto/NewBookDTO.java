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
    @JsonProperty
    private String name;

    @JsonProperty
    private String ISBN;

    @JsonProperty
    private String press;

    @JsonProperty
    private String edition;

    @JsonProperty
    private LocalDate published;

    /**
     * 图书最底层分类编号
     */
    @JsonProperty
    private long categoryID;

    @JsonProperty
    private BigDecimal price;

    /**
     * 图书装帧
     */
    @JsonProperty
    private String type;

    @JsonProperty
    private int amount;

    @JsonProperty
    private String brief;

    /**
     * 图书的作家ID列表
     */
    @JsonProperty
    private List<Long> authors;

    @JsonProperty
    private String url;

    @JsonProperty
    private int page;
}
