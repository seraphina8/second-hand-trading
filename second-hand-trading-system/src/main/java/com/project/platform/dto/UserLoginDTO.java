package com.project.platform.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserLoginDTO extends SmsCodeVerifyDTO {

    @Length(min = 2, max = 15, message = "用户名长度需要在[2和15]之间")
    @ApiModelProperty("用户名")
    private String username;
    @Length(min = 2, max = 15, message = "密码需要在[2和15]之间")
    @ApiModelProperty("密码")
    private String password;

}
