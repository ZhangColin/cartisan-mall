package com.cartisan.mall.system.user.controller;

import com.cartisan.mall.system.user.application.LoginAppService;
import com.cartisan.mall.system.user.request.LoginCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.cartisan.response.ResponseUtil.success;

/**
 * @author colin
 */
@Api(tags = "系统管理：登录")
@RestController
@Validated
@Slf4j
public class LoginController {
    private final LoginAppService loginAppService;

    public LoginController(LoginAppService loginAppService) {
        this.loginAppService = loginAppService;
    }

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @ApiParam(value = "登录信息", required = true) @Validated @RequestBody LoginCommand loginCommand) {
        return success(loginAppService.login(loginCommand));
    }

    @ApiOperation(value = "退出")
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        loginAppService.logout();
        return success();
    }

    @ApiOperation(value = "登录用户信息")
    @GetMapping("/user/info")
    public ResponseEntity<?> info() {
        return success(loginAppService.info());
    }
}