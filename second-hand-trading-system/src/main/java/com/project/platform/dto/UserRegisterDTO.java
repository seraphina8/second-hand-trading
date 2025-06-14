package com.project.platform.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterDTO extends SmsCodeVerifyDTO {

    @Length(min = 2, max = 15, message = "用户名长度需要在[2和15]之间")
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 2, max = 15, message = "密码需要在[2和15]之间")
    @ApiModelProperty("密码")
    private String password;


}
