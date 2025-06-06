package com.project.platform.entity;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单
 * </p>
 *
 *
 *
 */
@Getter
@Setter
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "菜单")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("上级菜单")
    private Integer parentId;

    @ApiModelProperty("菜单类型")
    private Integer type;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("外链")
    private Integer outsideChain;

    @ApiModelProperty("路由地址")
    private String address;

    @ApiModelProperty("组件地址")
    private String component;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private List<SysMenu> children;

    public void buildMenuList(List<SysMenu> menuList) {
        if (getStatus() != 1) {
            return;
        }
        if (CollectionUtil.isNotEmpty(children)) {
            for (SysMenu menu : children) {
                menu.buildMenuList(menuList);
            }
        }
        if (getType() != 2) {
            return;
        }
        SysMenu menu = new SysMenu();
        menu.setName(getName());
        menu.setAddress(getAddress());
        menu.setComponent(getComponent());
        menuList.add(menu);
    }

}
