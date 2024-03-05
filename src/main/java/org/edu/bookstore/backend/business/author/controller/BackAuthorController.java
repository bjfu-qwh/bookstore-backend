package org.edu.bookstore.backend.business.author.controller;

import org.edu.bookstore.backend.business.author.entity.AuthorInfo;
import org.edu.bookstore.backend.business.author.service.BackAuthorService;
import org.edu.bookstore.backend.dto.ResultDTO;
import org.edu.bookstore.backend.util.AuthenticationUtil;
import org.springframework.web.bind.annotation.*;

import static org.edu.bookstore.backend.business.ums.constant.UserRole.ROLE_WORKER;

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
    public ResultDTO<String> addAuthor(@RequestHeader("Authorization") String token,
                                       @RequestParam("workerID") String workerID,
                                       @RequestBody AuthorInfo authorInfo) {
        ResultDTO<String> check = authenticationUtil.checkToken(token, workerID, ROLE_WORKER);
        if (check != null) {
            return check;
        }
        return authorService.addAuthor(authorInfo);
    }
}
