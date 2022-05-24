package com.cmgzs.api.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserParam {

    @NotBlank(message = "用户名不能为空")
    @Length(min = 6, max = 16)
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 16)
    private String password;
}
