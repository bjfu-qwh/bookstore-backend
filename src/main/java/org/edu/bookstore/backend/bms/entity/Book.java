package org.edu.bookstore.backend.bms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.edu.bookstore.backend.bms.constant.BookStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_bms_book")
public class Book {
    /**
     * 这个是数据表的主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    @JsonProperty
    private String id;

    /**
     * 图书ISBN号码，唯一。
     */
    @TableField(value = "isbn")
    @JsonProperty
    private String isbn;

    /**
     * 图书名
     */
    @TableField(value = "book_name")
    @JsonProperty(value = "name")
    private String bookName;

    /**
     * 图书出版社名
     */
    @TableField(value = "press")
    @JsonProperty
    private String press;

    /**
     * 图书价格
     */
    @TableField(value = "price")
    @JsonProperty
    private BigDecimal price;

    /**
     * 图书页数
     */
    @TableField(value = "page")
    @JsonProperty
    private Integer page;

    /**
     * 图书版本描述
     */
    @TableField(value = "edition")
    @JsonProperty
    private String edition;

    /**
     * 本版图书出版时间
     */
    @TableField(value = "published")
    @JsonProperty
    private LocalDate published;

    /**
     * 图书库存
     */
    @TableField(value = "amount")
    @JsonProperty
    private int amount;

    /**
     * 图书装帧
     */
    @TableField(value = "type")
    @JsonProperty
    private String type;

    /**
     * 图书状态，如“无货”或“不再销售”等
     */
    @TableField(value = "status")
    @JsonProperty(defaultValue = BookStatus.STATUS_ON_SALE)
    private String status;

    /**
     * 图书图片信息
     */
    @TableField(value = "url")
    @JsonProperty
    private String url;
}
