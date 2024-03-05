package org.edu.bookstore.backend.business.author.mapper;


import org.apache.ibatis.annotations.Param;
import org.edu.bookstore.backend.business.author.entity.AuthorInfo;

public interface BackendAuthorMapper {
    /**
     * 注册一个新作家信息
     *
     * @param info 作家信息
     * @return 注册结果
     */
    int addAuthor(@Param("author") AuthorInfo info);
}
