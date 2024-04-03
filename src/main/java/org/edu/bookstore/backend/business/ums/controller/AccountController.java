package org.edu.bookstore.backend.business.ums.controller;

import org.edu.bookstore.backend.business.ums.dto.LoginDTO;
import org.edu.bookstore.backend.business.ums.dto.UserRegisterDTO;
import org.edu.bookstore.backend.business.ums.dto.UserTokenDTO;
import org.edu.bookstore.backend.business.ums.service.AccountService;
import org.edu.bookstore.backend.dto.JSONResult;
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
    public JSONResult<UserTokenDTO> register(@RequestBody UserRegisterDTO user) {
        return accountService.register(user);
    }

    @PostMapping("login")
    public JSONResult<UserTokenDTO> login(@RequestBody LoginDTO loginDTO) {
        return accountService.login(loginDTO);
    }

    @GetMapping("check-email")
    public JSONResult<String> checkEmail(@RequestParam("email") String email) {
        return accountService.checkEmail(email);
    }

    @GetMapping("check-phone")
    public JSONResult<String> checkPhone(@RequestParam("phone") String phone) {
        return accountService.checkPhone(phone);
    }
}
