package com.project.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import com.project.platform.service.SysUserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 收货地址
 * </p>
 *
 *
 *
 */
@Getter
@Setter
@TableName("shipping_address")
@ApiModel(value = "ShippingAddress对象", description = "收货地址")
public class ShippingAddress extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户")
    private Integer userId;

    @ApiModelProperty("收货人")
    private String name;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("默认地址")
    private Integer isDefault;

    @ApiModelProperty("创建时间")
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private String userName;

    public void selectUserName(SysUserService service) {
        SysUser user = service.getById(this.userId);
        if (user == null) {
            return;
        }
        this.userName = user.getUsername();
    }
}
