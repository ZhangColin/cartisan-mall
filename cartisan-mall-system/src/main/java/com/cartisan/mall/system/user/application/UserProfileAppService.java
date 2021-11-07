package com.cartisan.mall.system.user.application;

import com.cartisan.mall.system.security.CurrentUser;
import com.cartisan.mall.system.user.UserRepository;
import com.cartisan.mall.system.user.domain.ChangePasswordService;
import com.cartisan.mall.system.user.domain.User;
import com.cartisan.mall.system.user.request.ChangePasswordCommand;
import com.cartisan.security.LoginService;
import com.cartisan.util.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.cartisan.util.AssertionUtil.requirePresent;


/**
 * @author colin
 */
@Service
@Slf4j
public class UserProfileAppService {
    private final LoginService loginService;
    private final ChangePasswordService changePasswordService;
    private final UserRepository repository;
    private final CurrentUser currentUser;

    public UserProfileAppService(
            ChangePasswordService changePasswordService,
            LoginService loginService,
            UserRepository repository,
            CurrentUser currentUser) {
        this.loginService = loginService;
        this.repository = repository;
        this.changePasswordService = changePasswordService;
        this.currentUser = currentUser;
    }

    @Transactional(rollbackOn = Exception.class)
    public void changePassword(ChangePasswordCommand changePasswordCommand) {
        final User user = requirePresent(repository.findById(currentUser.getUserId()));

        changePasswordService.changePassword(user,
                AesUtil.aesDecode(changePasswordCommand.getNewPassword()),
                AesUtil.aesDecode(changePasswordCommand.getOldPassword()));

        loginService.logoutByUsername(user.getUsername());

        repository.save(user);
    }
}