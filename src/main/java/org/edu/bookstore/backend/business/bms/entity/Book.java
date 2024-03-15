package org.edu.bookstore.backend.business.bms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @TableId(value = "id", type = IdType.AUTO)
    private long id;

    /**
     * 图书ISBN号码，唯一。
     */
    @TableField(value = "isbn")
    private String isbn;

    /**
     * 图书名
     */
    @TableField(value = "book_name")
    private String bookName;

    /**
     * 图书出版社名
     */
    @TableField(value = "press")
    private String press;

    /**
     * 图书根分类编号
     */
    @TableField(value = "category_id")
    private long categoryID;

    /**
     * 图书价格
     */
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 图书页数
     */
    @TableField(value = "page")
    private Integer page;

    /**
     * 图书版本描述
     */
    @TableField(value = "edition")
    private String edition;

    /**
     * 本版图书出版时间
     */
    @TableField(value = "published")
    private LocalDate published;

    /**
     * 图书库存
     */
    @TableField(value = "amount")
    private int amount;

    /**
     * 图书装帧
     */
    @TableField(value = "type")
    private String type;

    /**
     * 图书状态，如“无货”或“不再销售”等
     */
    @TableField(value = "status")
    private String status;
}
