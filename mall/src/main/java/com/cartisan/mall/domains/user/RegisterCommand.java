package com.cartisan.mall.domains.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author colin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommand {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * email
     */
    @NotBlank(message = "email不能为空")
    private String email;
}
