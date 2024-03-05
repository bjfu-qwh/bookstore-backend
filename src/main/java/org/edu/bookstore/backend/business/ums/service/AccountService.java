package org.edu.bookstore.backend.business.ums.service;

import lombok.extern.slf4j.Slf4j;
import org.edu.bookstore.backend.business.ums.dto.LoginDTO;
import org.edu.bookstore.backend.business.ums.dto.UserTokenDTO;
import org.edu.bookstore.backend.business.ums.entity.User;
import org.edu.bookstore.backend.business.ums.mapper.AccountMapper;
import org.edu.bookstore.backend.dto.ResultDTO;
import org.edu.bookstore.backend.util.JWTUtil;
import org.edu.bookstore.backend.util.ResultDTOUtil;
import org.edu.bookstore.backend.util.UUIDUtil;
import org.springframework.stereotype.Service;

/**
 * 负责实现账号相关的功能。包括用户登录、注册以及注销还有相关查询功能。
 */
@Service
@Slf4j
public class AccountService {
    private final AccountMapper accountMapper;

    private final JWTUtil jwtUtil;

    public AccountService(AccountMapper accountMapper, JWTUtil jwtUtil) {
        this.accountMapper = accountMapper;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 账号注册功能实现。假设前端已经做好了校验工作
     *
     * @param user 注册账号信息
     * @return 注册是否成功。
     */
    public ResultDTO<String> register(User user) {
        String uuid = UUIDUtil.getUUID();
        synchronized (accountMapper) {
            user.setId(uuid);
            if (accountMapper.register(user) != 0) {
                return ResultDTOUtil.successWithMessageOnly("账号注册成功");
            }
            return ResultDTOUtil.error("系统故障");
        }
    }

    /**
     * 登录功能实现。支持通过邮箱和手机号之一+密码登录。
     * <br>
     * 实现时，考虑同时可能有用户注册邮箱/手机号，使用独占锁进行防护。
     *
     * @param loginDTO 用户输入的登录信息。包括邮箱/手机号以及加密后的密码
     * @return 为用户进行相应提示
     */
    public ResultDTO<UserTokenDTO> login(LoginDTO loginDTO) {
        User user;
        synchronized (this.accountMapper) {
            user = accountMapper.getByPhone(loginDTO.getKey());
        }
        if (user == null) {
            synchronized (this.accountMapper) {
                user = accountMapper.getByEmail(loginDTO.getKey());
            }
            if (user != null) {
                return validateUser(user, loginDTO);
            }
            return ResultDTOUtil.errorNotFound("登录信息或密码错误,请重新尝试");
        }
        return validateUser(user, loginDTO);
    }

    private ResultDTO<UserTokenDTO> validateUser(User user, LoginDTO loginDTO) {
        String password = user.getPassword();
        if (password.equals(loginDTO.getPassword())) {
            accountMapper.updateLastVisit(user.getId());
            return ResultDTOUtil.success(String.format("欢迎你,%s", user.getUsername()),
                    fromUserToUserToken(user));
        }
        return ResultDTOUtil.errorNotFound("登录信息或密码错误,请重新尝试");
    }

    private UserTokenDTO fromUserToUserToken(User user) {
        UserTokenDTO userTokenDTO = new UserTokenDTO();
        userTokenDTO.setEmail(user.getEmail());
        userTokenDTO.setUsername(user.getUsername());
        userTokenDTO.setToken(jwtUtil.createJWT(user.getId()));
        return userTokenDTO;
    }
}
