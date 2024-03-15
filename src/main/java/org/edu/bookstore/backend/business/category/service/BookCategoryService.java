package org.edu.bookstore.backend.business.category.service;

import lombok.extern.slf4j.Slf4j;
import org.edu.bookstore.backend.business.category.entity.BookCategory;
import org.edu.bookstore.backend.business.category.mapper.BookCategoryMapper;
import org.edu.bookstore.backend.dto.ResultDTO;
import org.edu.bookstore.backend.util.ResultDTOUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookCategoryService {
    private final BookCategoryMapper categoryMapper;

    public BookCategoryService(BookCategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public ResultDTO<String> addCategory(BookCategory category) {
        if (isCategoryIDExists(category.getId()) != null) {
            return ResultDTOUtil.errorForbidden(
                    String.format("图书分类编号%s已被使用", category.getId()));
        }
        int result = categoryMapper.addCategory(category);
        return ResultDTOUtil.successWithMessageOnly(String.format("成功添加了%d个分类", result));
    }

    public ResultDTO<String> isCategoryIDExists(String id) {
        BookCategory category = categoryMapper.getByID(id);
        if (category != null) {
            return ResultDTOUtil.successWithMessageOnly(
                    String.format("该编号%s已被使用,请重新选择", id));
        }
        return ResultDTOUtil.successWithMessageOnly("可以使用的分类编号");
    }

    public ResultDTO<List<BookCategory>> allCategories() {
        return ResultDTOUtil.successWithDataOnly(categoryMapper.allCategories());
    }
}
