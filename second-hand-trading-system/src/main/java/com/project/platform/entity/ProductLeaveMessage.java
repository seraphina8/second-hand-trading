package com.project.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import com.project.platform.service.ProductService;
import com.project.platform.service.SysUserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品留言
 * </p>
 *
 *
 */
@Getter
@Setter
@TableName("product_leave_message")
@ApiModel(value = "ProductLeaveMessage对象", description = "商品留言")
public class ProductLeaveMessage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("发布人")
    private Integer userId;

    @ApiModelProperty("商品")
    private Integer productId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String userAvatarUrl;

    public void selectUserInfo(SysUserService service) {
        SysUser user = service.getById(userId);
        if (user != null) {
            userName = user.getUsername();
            userAvatarUrl = user.getImg();
        }
    }

    @TableField(exist = false)
    private Product product;
    public void selectSellerName(ProductService service) {
        product = service.getById(this.productId);
    }
}
