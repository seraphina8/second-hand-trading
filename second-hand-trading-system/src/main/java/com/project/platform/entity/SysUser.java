package com.project.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import com.project.platform.service.SysRoleService;
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
 */
@Getter
@Setter
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("个人简介")
    private String content;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("头像")
    private String img;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("角色")
    private Integer roleId;

    @ApiModelProperty("加盐")
    private String salt;




    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    @ApiModelProperty("角色")
    @TableField(exist = false)
    private SysRole role;

    public void selectRole(SysRoleService service) {
        this.role = service.getById(this.roleId);
    }
}
