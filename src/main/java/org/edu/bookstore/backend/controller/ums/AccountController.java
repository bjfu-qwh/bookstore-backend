package org.edu.bookstore.backend.controller.ums;

import org.edu.bookstore.backend.dto.ResultDTO;
import org.edu.bookstore.backend.dto.ums.LoginDTO;
import org.edu.bookstore.backend.dto.ums.UserTokenDTO;
import org.edu.bookstore.backend.entity.ums.User;
import org.edu.bookstore.backend.service.ums.AccountService;
import org.edu.bookstore.backend.util.AuthenticationUtil;
import org.edu.bookstore.backend.util.ResultDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/")
@CrossOrigin
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("register")
    public ResultDTO<String> register(@RequestBody User user) {
        return accountService.register(user);
    }

    @GetMapping("login")
    public ResultDTO<UserTokenDTO> login(@RequestBody LoginDTO loginDTO) {
        return accountService.login(loginDTO);
    }
}
