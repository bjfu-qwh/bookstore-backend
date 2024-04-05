package org.edu.bookstore.backend.business.bms.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edu.bookstore.backend.business.author.vo.BookAuthorVO;
import org.edu.bookstore.backend.business.category.entity.CategoryInfo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 简略的图书信息，可用于表格展示等。
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookVO implements Serializable {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty
    private String isbn;

    @JsonProperty(value = "bookName")
    private String bookName;

    @JsonProperty(value = "price")
    private BigDecimal price;

    /**
     * 图书的默认图片
     */
    @JsonProperty(value = "url")
    private String url;

    @JsonProperty
    private String press;

    /**
     * 图书所有分类信息
     */
    @JsonProperty
    private List<CategoryInfo> categories = null;

    /**
     * 图书所有作者信息
     */
    @JsonProperty
    private List<BookAuthorVO> authors = null;
}
