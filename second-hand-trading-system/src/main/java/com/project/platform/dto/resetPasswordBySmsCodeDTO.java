package com.project.platform.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class resetPasswordBySmsCodeDTO extends SmsCodeVerifyDTO {
    @ApiModelProperty("密码")
    private String password;

}
