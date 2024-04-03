package org.edu.bookstore.backend.business.category.service;

import lombok.extern.slf4j.Slf4j;
import org.edu.bookstore.backend.business.category.entity.BookCategory;
import org.edu.bookstore.backend.business.category.mapper.BookCategoryMapper;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.util.JSONResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookCategoryService {
    private final BookCategoryMapper categoryMapper;

    public BookCategoryService(BookCategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public JSONResult<String> addCategory(BookCategory category) {
        int result;
        synchronized (categoryMapper) {
            if (isCategoryIDExists(category.getId()) != null) {
                return JSONResultUtil.errorForbidden(
                        String.format("图书分类编号%s已被使用", category.getId()));
            }
            result = categoryMapper.addCategory(category);
        }
        return JSONResultUtil.successWithMessageOnly(String.format("成功添加了%d个分类", result));
    }

    public JSONResult<String> isCategoryIDExists(String id) {
        BookCategory category = categoryMapper.getByID(id);
        if (category != null) {
            return JSONResultUtil.successWithMessageOnly(
                    String.format("该编号%s已被使用,请重新选择", id));
        }
        return JSONResultUtil.successWithMessageOnly("可以使用的分类编号");
    }

    public JSONResult<List<BookCategory>> allCategories() {
        return JSONResultUtil.successWithDataOnly(categoryMapper.allCategories());
    }

    public JSONResult<List<BookCategory>> allChildren(String parentID) {
        return JSONResultUtil.successWithDataOnly(categoryMapper.allChildren(parentID));
    }
}
