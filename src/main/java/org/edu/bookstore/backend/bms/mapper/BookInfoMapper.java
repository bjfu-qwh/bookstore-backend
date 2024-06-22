package org.edu.bookstore.backend.bms.mapper;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 关于图书信息的mapper接口。主要是图书信息修改，所有可能修改的图书信息在这里进行详细拆分。
 */
public interface BookInfoMapper {
    int changeBookISBN(@Param("id") long bookID, @Param("isbn") String isbn);

    int changeBookName(@Param("id") long id, @Param("name") String name);

    int changeBookPress(@Param("id") long id, @Param("press") String press);

    int changeBookPrice(@Param("id") long id, @Param("price") BigDecimal price);

    int changeBookCategory(@Param("id") long id, @Param("category") long category);

    int changeBookAmount(@Param("id") long id, @Param("bias") int bias);
}
