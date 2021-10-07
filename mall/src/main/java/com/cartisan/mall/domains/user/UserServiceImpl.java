package com.cartisan.mall.domains.user;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * @author colin
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(RegisterCommand command) {
        if (userRepository.existsByUserName(command.getUserName())) {
            throw new RuntimeException("该username已注册");
        }
        if (userRepository.existsByEmail(command.getEmail())) {
            throw new RuntimeException("该email已注册");
        }

        final User user = new User(command.getUserName(),
                // MD5摘要算法(Spring自带)
                DigestUtils.md5DigestAsHex(command.getPassword().getBytes(StandardCharsets.UTF_8)),
                command.getEmail(),
                RoleEnum.CUSTOMER.getCode());

        return userRepository.save(user);
    }

    @Override
    public User login(LoginCommand command) {
        final Optional<User> userOptional = userRepository.findByUserName(command.getUserName());

        final User user = userOptional.orElseThrow(() -> new RuntimeException("用户名或密码错误"));

        if (!user.getPassword().equalsIgnoreCase(
                DigestUtils.md5DigestAsHex(command.getPassword().getBytes(StandardCharsets.UTF_8)))) {
            throw new RuntimeException("用户名或密码错误");
        }

        return user;
    }
}
