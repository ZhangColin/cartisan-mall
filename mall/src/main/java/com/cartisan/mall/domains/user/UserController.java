package com.cartisan.mall.domains.user;

import com.cartisan.mall.domains.consts.MallConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.cartisan.response.ResponseUtil.success;
import static com.cartisan.response.ResponseUtil.successWithMessage;

/**
 * @author colin
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterCommand command) {
//        if (bindingResult.hasErrors()) {
//            log.info("注册提交的参数有误，{} {}",
//                    bindingResult.getFieldError().getField(),
//                    bindingResult.getFieldError().getDefaultMessage());
//        }

        userService.register(command);

        return successWithMessage("注册成功");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginCommand command,
                                   HttpSession session) {
        final User user = userService.login(command);

        // 设置 Session
        session.setAttribute(MallConst.CURRENT_USER, user);

        return success(user);
    }

    @GetMapping
    public ResponseEntity<?> userInfo(HttpSession session) {
        final User user = (User) session.getAttribute(MallConst.CURRENT_USER);
        return success(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.removeAttribute(MallConst.CURRENT_USER);

        return success();
    }
}
