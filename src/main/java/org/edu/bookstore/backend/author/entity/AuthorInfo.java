package org.edu.bookstore.backend.author.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_author_info")
public class AuthorInfo implements Serializable {
    /**
     * 数据表主键url
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 作家姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 作家所属国籍
     */
    @TableField(value = "nation")
    private String nation;

    /**
     * 作家头像文件url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 作家生平介绍所在文件url
     */
    @TableField(value = "brief")
    private String brief;
}
