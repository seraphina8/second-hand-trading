package com.project.platform.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysUserDTO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("角色")
    private Integer roleId;


    public boolean isAdmin() {
        return this.roleId == 1;
    }

}
