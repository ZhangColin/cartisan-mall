package com.cartisan.mall.domains.user;

/**
 * @author colin
 */
public interface UserService {
    /**
     * 注册
     */
    User register(RegisterCommand command);

    /**
     * 登录
     */
    User login(LoginCommand command);
}
