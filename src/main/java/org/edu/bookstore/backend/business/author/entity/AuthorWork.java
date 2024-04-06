package org.edu.bookstore.backend.business.author.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_author_work")
public class AuthorWork {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "work_id")
    private String workID;

    @TableField(value = "author_id")
    private long authorID;
}
