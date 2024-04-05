package org.edu.bookstore.backend.business.bms.controller;

import org.edu.bookstore.backend.business.bms.dto.NewBookDTO;
import org.edu.bookstore.backend.business.bms.service.BackBookService;
import org.edu.bookstore.backend.business.bms.vo.BookVO;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.dto.PagedDTO;
import org.edu.bookstore.backend.util.AuthenticationUtil;
import org.edu.bookstore.backend.util.JSONResultUtil;
import org.springframework.web.bind.annotation.*;

import static org.edu.bookstore.backend.business.ums.constant.UserRole.ROLE_WORKER;

@RestController
@CrossOrigin
@RequestMapping("/back/bms/book/")
public class BackBookController {
    private final BackBookService backBookService;

    private final AuthenticationUtil authenticationUtil;

    public BackBookController(BackBookService backBookService, AuthenticationUtil authenticationUtil) {
        this.backBookService = backBookService;
        this.authenticationUtil = authenticationUtil;
    }

    @PostMapping("add")
    public JSONResult<String> addBook(@RequestHeader("Authorization") String token,
                                      @RequestBody NewBookDTO book,
                                      @RequestParam("workerID") String workerID) {
        JSONResult<String> check = authenticationUtil.checkTokenAndUserRole(token, workerID, ROLE_WORKER);
        if (check != null) {
            return check;
        }
        return backBookService.addBook(book);
    }

    @GetMapping("isbn")
    public JSONResult<Boolean> checkISBN(@RequestParam("isbn") String isbn) {
        return backBookService.checkISBN(isbn);
    }

    @GetMapping("page")
    public JSONResult<PagedDTO<BookVO>> getBookVOPage(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "pageID", defaultValue = "1") int pageID,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
        JSONResult<String> check = authenticationUtil.checkTokenOnly(token);
        if (check != null) {
            return JSONResultUtil.errorForbidden(check.getMessage());
        }
        return backBookService.getBookVOPage(pageID, pageSize);
    }

    @GetMapping("search")
    public JSONResult<PagedDTO<BookVO>> searchBookVOPage(
            @RequestHeader("Authorization") String token,
            @RequestParam(value = "pageID", defaultValue = "1") int pageID,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
            @RequestParam(value = "query", defaultValue = "") String query
    ) {
        JSONResult<String> check = authenticationUtil.checkTokenOnly(token);
        if (check != null) {
            return JSONResultUtil.errorForbidden(check.getMessage());
        }
        if (query.isEmpty()) {
            return backBookService.getBookVOPage(pageID, pageSize);
        }
        return backBookService.searchBookVOPage(pageID, pageSize, query);
    }
}
