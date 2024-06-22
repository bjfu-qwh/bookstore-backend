package org.edu.bookstore.backend.category.mapper;

import org.apache.ibatis.annotations.Param;
import org.edu.bookstore.backend.category.entity.CategoryInfo;

import java.util.List;


public interface CategoryInfoMapper {
    int addCategory(@Param("category") CategoryInfo category);

    CategoryInfo getByID(@Param("id") String id);

    List<CategoryInfo> allCategories();

    List<CategoryInfo> allChildren(@Param("parent") String parent);
}
