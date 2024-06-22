package org.edu.bookstore.backend.author.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.edu.bookstore.backend.author.entity.AuthorWork;

public interface BackAuthorWorkMapper extends BaseMapper<AuthorWork> {
    int addAuthorWork(@Param("author_id") Long authorID, @Param("work_id") String workID);
}
