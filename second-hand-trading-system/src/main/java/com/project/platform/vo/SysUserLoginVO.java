package com.project.platform.vo;

import com.project.platform.entity.SysMenu;
import com.project.platform.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SysUserLoginVO {

    @ApiModelProperty("id")

    private Integer id;
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("个人简介")
    private String content;

    @ApiModelProperty("头像")
    private String img;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("电话")
    private String mobile;

    @ApiModelProperty("token认证")
    private String token;

    @ApiModelProperty("角色")
    private SysRole role;


    private List<SysMenu> menuTree;


    public List<SysMenu> getMenuList() {
        List<SysMenu> buildMenuList = new ArrayList<>();
        for (SysMenu menu : this.menuTree) {
            menu.buildMenuList(buildMenuList);
        }
        return buildMenuList;
    }


}
