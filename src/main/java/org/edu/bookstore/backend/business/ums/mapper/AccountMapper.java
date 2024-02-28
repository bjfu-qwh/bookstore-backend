package org.edu.bookstore.backend.business.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.edu.bookstore.backend.business.ums.entity.User;

public interface AccountMapper extends BaseMapper<User> {
    User getByUserID(@Param("user_id") String userID);

    int register(@Param("user") User user);

    User getByPhone(@Param("phone") String phone);

    User getByEmail(@Param("email") String email);

    int updateLastVisit(@Param("user_id") String userID);
}
