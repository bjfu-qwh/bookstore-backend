package org.edu.bookstore.backend.bms.controller;

import org.edu.bookstore.backend.bms.service.BackBookInfoService;
import org.edu.bookstore.backend.bms.service.BackBookService;
import org.edu.bookstore.backend.bms.vo.BookVO;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.dto.PagedDTO;
import org.edu.bookstore.backend.util.AuthenticationUtil;
import org.edu.bookstore.backend.util.JSONResultUtil;
import org.springframework.web.bind.annotation.*;

import static org.edu.bookstore.backend.ums.constant.UserRole.ROLE_WORKER;

@RestController
@RequestMapping("/back/bms/info/")
@CrossOrigin
public class BackBookInfoController {
    private final BackBookInfoService infoService;
    private final AuthenticationUtil authenticationUtil;
    private final BackBookService backBookService;

    public BackBookInfoController(BackBookInfoService infoService, AuthenticationUtil authenticationUtil, BackBookService backBookService) {
        this.infoService = infoService;
        this.authenticationUtil = authenticationUtil;
        this.backBookService = backBookService;
    }

    @PostMapping("change-name")
    public JSONResult<String> changeBookName(@RequestParam("workerID") String workerID,
                                             @RequestHeader("Authorization") String token,
                                             @RequestParam("bookID") long bookID,
                                             @RequestParam("bookName") String name) {
        JSONResult<String> check = authenticationUtil.checkTokenAndUserRole(token, workerID, ROLE_WORKER);
        if (check != null) {
            return check;
        }
        return infoService.changeBookName(bookID, name, workerID);
    }

    @GetMapping("table")
    public JSONResult<PagedDTO<BookVO>> getBookTable(@RequestHeader("Authorization") String token,
                                                     @RequestParam(name = "pageID", defaultValue = "1") Integer pageID,
                                                     @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        JSONResult<String> check = authenticationUtil.checkTokenOnly(token);
        if (check != null) {
            return JSONResultUtil.error(check.getMessage());
        }
        return backBookService.getBookTable(pageID, pageSize);
    }
}
