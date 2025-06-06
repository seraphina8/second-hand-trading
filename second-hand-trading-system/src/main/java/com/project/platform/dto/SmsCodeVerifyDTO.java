package com.project.platform.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SmsCodeVerifyDTO {
    @ApiModelProperty("电话")
    private String mobile;

    @ApiModelProperty("短信验证码")
    private String smsCode;
}
