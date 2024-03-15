package org.edu.bookstore.backend.business.category.controller;

import org.edu.bookstore.backend.business.category.entity.BookCategory;
import org.edu.bookstore.backend.business.category.service.BookCategoryService;
import org.edu.bookstore.backend.dto.ResultDTO;
import org.edu.bookstore.backend.util.AuthenticationUtil;
import org.edu.bookstore.backend.util.ResultDTOUtil;
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
    public ResultDTO<List<BookCategory>> allCategories(@RequestHeader("Authorization") String token) {
        ResultDTO<String> check = authenticationUtil.checkTokenOnly(token);
        if (check != null) {
            return ResultDTOUtil.errorUnAuthorized(check.getMessage());
        }
        return bookCategoryService.allCategories();
    }

    @PostMapping("add")
    public ResultDTO<String> addCategory(@RequestHeader("Authorization") String token,
                                         @RequestParam("workerID") String workerID,
                                         @RequestBody BookCategory category) {
        ResultDTO<String> check = authenticationUtil.checkTokenAndUserRole(token, workerID, ROLE_WORKER);
        if (check != null) {
            return check;
        }
        return bookCategoryService.addCategory(category);
    }
}
