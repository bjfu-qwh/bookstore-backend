package org.edu.bookstore.backend.ums.service;

import lombok.extern.slf4j.Slf4j;
import org.edu.bookstore.backend.ums.dto.LoginDTO;
import org.edu.bookstore.backend.ums.dto.UserRegisterDTO;
import org.edu.bookstore.backend.ums.dto.UserTokenDTO;
import org.edu.bookstore.backend.ums.entity.User;
import org.edu.bookstore.backend.ums.mapper.AccountMapper;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.ums.constant.UserRole;
import org.edu.bookstore.backend.util.JSONResultUtil;
import org.edu.bookstore.backend.util.JWTUtil;
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
     * @param info 注册账号信息
     * @return 注册是否成功。
     */
    public JSONResult<UserTokenDTO> register(UserRegisterDTO info) {
        String uuid = UUIDUtil.getUUID();
        User user = infoToWorker(info);
        user.setId(uuid);
        if (accountMapper.register(user) != 0) {
            return JSONResultUtil.success("注册成功",
                    new UserTokenDTO(
                            uuid,
                            user.getUsername(),
                            user.getEmail(),
                            jwtUtil.createJWT(uuid),
                            user.getUrl()
                    )
            );
        }
        return JSONResultUtil.error("系统故障");
    }

    private User infoToWorker(UserRegisterDTO info) {
        User user = new User();
        user.setUrl(info.getUrl());
        user.setUsername(info.getUsername());
        user.setEmail(info.getEmail());
        user.setPhone(info.getPhone());
        user.setPassword(info.getPassword());
        user.setRole(UserRole.ROLE_WORKER);
        return user;
    }

    /**
     * 登录功能实现。支持通过邮箱和手机号之一+密码登录。
     * <br>
     * 实现时，考虑同时可能有用户注册邮箱/手机号，使用独占锁进行防护。
     *
     * @param loginDTO 用户输入的登录信息。包括邮箱/手机号以及加密后的密码
     * @return 为用户进行相应提示
     */
    public JSONResult<UserTokenDTO> login(LoginDTO loginDTO) {
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
            return JSONResultUtil.errorNotFound("登录信息或密码错误,请重新尝试");
        }
        return validateUser(user, loginDTO);
    }

    private JSONResult<UserTokenDTO> validateUser(User user, LoginDTO loginDTO) {
        String password = user.getPassword();
        if (password.equals(loginDTO.getPassword())) {
            accountMapper.updateLastVisit(user.getId());
            return JSONResultUtil.success(String.format("欢迎你,%s", user.getUsername()),
                    fromUserToUserToken(user));
        }
        return JSONResultUtil.errorNotFound("登录信息或密码错误,请重新尝试");
    }

    private UserTokenDTO fromUserToUserToken(User user) {
        UserTokenDTO userTokenDTO = new UserTokenDTO();
        userTokenDTO.setEmail(user.getEmail());
        userTokenDTO.setId(user.getId());
        userTokenDTO.setUrl(user.getUrl());
        userTokenDTO.setUsername(user.getUsername());
        userTokenDTO.setToken(jwtUtil.createJWT(user.getId()));
        return userTokenDTO;
    }

    public JSONResult<String> checkEmail(String email) {
        if (accountMapper.getByEmail(email) != null) {
            return JSONResultUtil.errorForbidden("该邮箱已被注册，请更换邮箱");
        }
        return JSONResultUtil.successWithMessageOnly("该邮箱可以使用");
    }

    public JSONResult<String> checkPhone(String phone) {
        if (accountMapper.getByPhone(phone) != null) {
            return JSONResultUtil.errorForbidden("该手机号码已被注册，请更换");
        }
        return JSONResultUtil.successWithMessageOnly("该手机号可以使用");
    }
}
