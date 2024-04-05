package org.edu.bookstore.backend.business.category.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 表示一种图书分类
 */
@TableName(value = "t_category_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryInfo implements Serializable {
    @TableId(value = "id", type = IdType.INPUT)
    @JsonProperty
    private String id;

    @TableField(value = "name")
    @JsonProperty
    private String name;

    @TableField(value = "parent")
    @JsonProperty
    private String parent;
}
