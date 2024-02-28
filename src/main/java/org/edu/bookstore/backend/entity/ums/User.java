package org.edu.bookstore.backend.entity.ums;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * User表示一个账号
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@TableName("t_ums_user")
public class User {
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @TableField(value = "username")
    private String username;

    @JsonIgnore
    @TableField(value = "password")
    private String password;

    @TableField(value = "email")
    private String email;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "role")
    private String role;

    @TableField(value = "url")
    private String url;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "last_visit")
    private LocalDateTime lastVisit;
}
