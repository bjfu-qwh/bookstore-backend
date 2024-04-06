package org.edu.bookstore.backend.business.category.controller;

import org.edu.bookstore.backend.business.category.entity.CategoryInfo;
import org.edu.bookstore.backend.business.category.service.CategoryService;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.util.AuthenticationUtil;
import org.edu.bookstore.backend.util.JSONResultUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.edu.bookstore.backend.business.ums.constant.UserRole.ROLE_WORKER;

@RestController
@CrossOrigin
@RequestMapping("/category/")
public class CategoryController {
    private final AuthenticationUtil authenticationUtil;
    private final CategoryService categoryService;

    public CategoryController(AuthenticationUtil authenticationUtil, CategoryService categoryService) {
        this.authenticationUtil = authenticationUtil;
        this.categoryService = categoryService;
    }


    @GetMapping("all")
    public JSONResult<List<CategoryInfo>> allCategories(@RequestHeader("Authorization") String token) {
        JSONResult<String> check = authenticationUtil.checkTokenOnly(token);
        if (check != null) {
            return JSONResultUtil.errorUnAuthorized(check.getMessage());
        }
        return categoryService.allCategories();
    }

    @PostMapping("add")
    public JSONResult<String> addCategory(@RequestHeader("Authorization") String token,
                                          @RequestParam("workerID") String workerID,
                                          @RequestBody CategoryInfo category) {
        JSONResult<String> check = authenticationUtil.checkTokenAndUserRole(token, workerID, ROLE_WORKER);
        if (check != null) {
            return check;
        }
        return categoryService.addCategory(category);
    }

    @GetMapping("children")
    public JSONResult<List<CategoryInfo>> allChildren(@RequestParam(value = "parent", defaultValue = "") String parent) {
        return categoryService.allChildren(parent);
    }

    @GetMapping("get")
    public JSONResult<CategoryInfo> getByID(@RequestParam("id") String categoryID) {
        return categoryService.getByCategoryID(categoryID);
    }

    @GetMapping("check-id")
    public JSONResult<Boolean> checkCategoryID(@RequestParam("id") String id) {
        return categoryService.checkCategoryID(id);
    }
}
