package com.project.platform.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色菜单
 * </p>
 *
 *
 *
 */
@Getter
@Setter
@TableName("sys_role_menu")
@ApiModel(value = "RoleMenu对象", description = "角色菜单")
public class SysRoleMenu {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    private Integer roleId;

    @ApiModelProperty("菜单ID")
    private Integer menuId;
}
