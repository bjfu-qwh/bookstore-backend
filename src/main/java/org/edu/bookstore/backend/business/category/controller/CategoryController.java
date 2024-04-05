package org.edu.bookstore.backend.business.category.controller;

import org.edu.bookstore.backend.business.category.entity.CategoryInfo;
import org.edu.bookstore.backend.business.category.service.BookCategoryService;
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
    private final BookCategoryService bookCategoryService;

    public CategoryController(AuthenticationUtil authenticationUtil, BookCategoryService bookCategoryService) {
        this.authenticationUtil = authenticationUtil;
        this.bookCategoryService = bookCategoryService;
    }


    @GetMapping("all")
    public JSONResult<List<CategoryInfo>> allCategories(@RequestHeader("Authorization") String token) {
        JSONResult<String> check = authenticationUtil.checkTokenOnly(token);
        if (check != null) {
            return JSONResultUtil.errorUnAuthorized(check.getMessage());
        }
        return bookCategoryService.allCategories();
    }

    @PostMapping("add")
    public JSONResult<String> addCategory(@RequestHeader("Authorization") String token,
                                          @RequestParam("workerID") String workerID,
                                          @RequestBody CategoryInfo category) {
        JSONResult<String> check = authenticationUtil.checkTokenAndUserRole(token, workerID, ROLE_WORKER);
        if (check != null) {
            return check;
        }
        return bookCategoryService.addCategory(category);
    }

    @GetMapping("children")
    public JSONResult<List<CategoryInfo>> allChildren(@RequestParam(value = "parent", defaultValue = "") String parent) {
        return bookCategoryService.allChildren(parent);
    }
}
