package org.edu.bookstore.backend.business.category.mapper;

import org.apache.ibatis.annotations.Param;
import org.edu.bookstore.backend.business.category.entity.BookCategory;

import java.util.List;


public interface BookCategoryMapper {
    int addCategory(@Param("category") BookCategory category);

    BookCategory getByID(@Param("id") String id);

    List<BookCategory> allCategories();

    List<BookCategory> allChildren(@Param("parent") String parent);
}
