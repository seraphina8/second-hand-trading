package com.project.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.List;
import java.util.stream.Collectors;

import com.project.platform.service.SysRoleMenuService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 *
 *
 */
@Getter
@Setter
@TableName("sys_role")
@ApiModel(value = "SysRole对象", description = "")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色描述")
    private String content;

    @ApiModelProperty("角色字段")
    private String code;


    @TableField(exist = false)
    private List<Integer> menuIds;


    public void selectMenuList(SysRoleMenuService service) {
        menuIds = service.list(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, getId())).stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
    }
}
