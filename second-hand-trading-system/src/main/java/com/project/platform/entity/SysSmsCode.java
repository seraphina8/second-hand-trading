package com.project.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 短信验证码
 * </p>
 *
 *
 *
 */
@Getter
@Setter
@TableName("sys_sms_code")
@ApiModel(value = "SysSmsCode对象", description = "短信验证码")
public class SysSmsCode extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("验证码")
    private String code;

    @ApiModelProperty("短信内容")
    private String content;

    @ApiModelProperty("过期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expirationTime;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
