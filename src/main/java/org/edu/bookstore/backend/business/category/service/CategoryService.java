package org.edu.bookstore.backend.business.category.service;

import lombok.extern.slf4j.Slf4j;
import org.edu.bookstore.backend.business.category.entity.CategoryInfo;
import org.edu.bookstore.backend.business.category.mapper.CategoryInfoMapper;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.util.JSONResultUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {
    private final CategoryInfoMapper categoryMapper;

    public CategoryService(CategoryInfoMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public JSONResult<String> addCategory(CategoryInfo category) {
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
        CategoryInfo category = categoryMapper.getByID(id);
        if (category != null) {
            return JSONResultUtil.successWithMessageOnly(
                    String.format("该编号%s已被使用,请重新选择", id));
        }
        return null;
    }

    public JSONResult<List<CategoryInfo>> allCategories() {
        return JSONResultUtil.successWithDataOnly(categoryMapper.allCategories());
    }

    public JSONResult<List<CategoryInfo>> allChildren(String parentID) {
        return JSONResultUtil.successWithDataOnly(categoryMapper.allChildren(parentID));
    }

    public JSONResult<CategoryInfo> getByCategoryID(String categoryID) {
        return JSONResultUtil.successWithDataOnly(categoryMapper.getByID(categoryID));
    }

    public JSONResult<Boolean> checkCategoryID(String id) {
        CategoryInfo info = categoryMapper.getByID(id);
        boolean exists = (info != null);
        return JSONResultUtil.successWithDataOnly(exists);
    }
}
