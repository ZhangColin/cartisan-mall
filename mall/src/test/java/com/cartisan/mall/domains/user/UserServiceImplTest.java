package com.cartisan.mall.domains.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * @author colin
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceImplTest  {
    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        final RegisterCommand registerCommand = new RegisterCommand("colin","123456","colin@cartisan.com");

        userService.register(registerCommand);
    }

    @Test
    public void register() {
    }

    @Test
    public void login() {
        final LoginCommand loginCommand = new LoginCommand("colin","123456");

        final User user = userService.login(loginCommand);

        Assert.assertNotNull(user);
    }
}
