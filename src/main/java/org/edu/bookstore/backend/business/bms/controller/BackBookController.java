package org.edu.bookstore.backend.business.bms.controller;

import org.edu.bookstore.backend.business.bms.entity.Book;
import org.edu.bookstore.backend.business.bms.service.BackBookService;
import org.edu.bookstore.backend.dto.ResultDTO;
import org.edu.bookstore.backend.util.AuthenticationUtil;
import org.springframework.web.bind.annotation.*;

import static org.edu.bookstore.backend.business.ums.constant.UserRole.ROLE_WORKER;

@RestController
@RequestMapping("/back/bms/book/")
public class BackBookController {
    private final BackBookService backBookService;

    private final AuthenticationUtil authenticationUtil;

    public BackBookController(BackBookService backBookService, AuthenticationUtil authenticationUtil) {
        this.backBookService = backBookService;
        this.authenticationUtil = authenticationUtil;
    }

    @PostMapping("add")
    public ResultDTO<String> addBook(@RequestHeader("Authorization") String token,
                                     @RequestBody Book book,
                                     String workerID) {
        ResultDTO<String> check = authenticationUtil.checkToken(token, workerID, ROLE_WORKER);
        if (check != null) {
            return check;
        }
        return backBookService.addBook(book);
    }
}
