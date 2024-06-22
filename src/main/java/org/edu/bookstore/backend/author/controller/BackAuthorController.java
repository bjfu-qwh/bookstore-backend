package org.edu.bookstore.backend.author.controller;

import org.edu.bookstore.backend.author.dto.AuthorSelectItemDTO;
import org.edu.bookstore.backend.author.entity.AuthorInfo;
import org.edu.bookstore.backend.author.service.BackAuthorService;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.util.AuthenticationUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.edu.bookstore.backend.ums.constant.UserRole.ROLE_WORKER;

@RestController
@CrossOrigin
@RequestMapping("/back/author/")
public class BackAuthorController {
    private final BackAuthorService authorService;

    private final AuthenticationUtil authenticationUtil;

    public BackAuthorController(BackAuthorService authorService, AuthenticationUtil authenticationUtil) {
        this.authorService = authorService;
        this.authenticationUtil = authenticationUtil;
    }

    @PostMapping("add")
    public JSONResult<String> addAuthor(@RequestHeader("Authorization") String token,
                                        @RequestParam("workerID") String workerID,
                                        @RequestBody AuthorInfo authorInfo) {
        JSONResult<String> check = authenticationUtil.checkTokenAndUserRole(token, workerID, ROLE_WORKER);
        if (check != null) {
            return check;
        }
        return authorService.addAuthor(authorInfo);
    }

    @GetMapping("selector")
    public JSONResult<List<AuthorSelectItemDTO>> allAuthorSelectorItem() {
        return authorService.allAuthorSelectorItem();
    }
}
