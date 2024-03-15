package org.edu.bookstore.backend.business.bms.controller;

import org.edu.bookstore.backend.business.bms.service.BackBookInfoService;
import org.edu.bookstore.backend.dto.ResultDTO;
import org.edu.bookstore.backend.util.AuthenticationUtil;
import org.springframework.web.bind.annotation.*;

import static org.edu.bookstore.backend.business.ums.constant.UserRole.ROLE_WORKER;

@RestController
@RequestMapping("/back/bms/info/")
@CrossOrigin
public class BackBookInfoController {
    private final BackBookInfoService infoService;
    private final AuthenticationUtil authenticationUtil;

    public BackBookInfoController(BackBookInfoService infoService, AuthenticationUtil authenticationUtil) {
        this.infoService = infoService;
        this.authenticationUtil = authenticationUtil;
    }

    @PostMapping("change-name")
    public ResultDTO<String> changeBookName(@RequestParam("workerID") String workerID,
                                            @RequestHeader("Authorization") String token,
                                            @RequestParam("bookID") long bookID,
                                            @RequestParam("bookName") String name) {
        ResultDTO<String> check = authenticationUtil.checkTokenAndUserRole(token, workerID, ROLE_WORKER);
        if (check != null) {
            return check;
        }
        return infoService.changeBookName(bookID, name, workerID);
    }
}
