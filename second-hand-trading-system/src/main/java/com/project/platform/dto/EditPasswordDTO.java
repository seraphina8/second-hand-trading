package com.project.platform.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class EditPasswordDTO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("新密码")
    private String newPassword;

}
