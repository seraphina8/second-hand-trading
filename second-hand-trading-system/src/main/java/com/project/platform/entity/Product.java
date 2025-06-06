package com.project.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

import com.project.platform.service.ProductFavoriteService;
import com.project.platform.service.SysUserService;
import com.project.platform.utils.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品
 * </p>
 *
 *
 */
@Getter
@Setter
@ApiModel(value = "Product对象", description = "商品")
public class Product extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标题")
    private String name;

    @ApiModelProperty("说明")
    private String content;

    @ApiModelProperty("图片")
    private String img;

    @ApiModelProperty("视频")
    private String video;

    @ApiModelProperty("分类")
    private Integer productClassifyId;

    @ApiModelProperty("成色")
    private Integer productFinenessId;

    @ApiModelProperty("原价")
    private Double originalPrice;

    @ApiModelProperty("价格")
    private Double price;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("发布人")
    private Integer userId;

    @ApiModelProperty("浏览量")
    private Integer pageView;

    @ApiModelProperty("想要")
    private Integer want;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String userAvatarUrl;


    @TableField(exist = false)
    private int weight;
    public void selectUserName(SysUserService service) {
        SysUser user = service.getById(this.userId);
        if (user == null) {
            return;
        }
        this.userName = user.getUsername();
    }




    public void selectUserInfo(SysUserService service) {
        SysUser user = service.getById(userId);
        if (user != null) {
            userName = user.getUsername();
            userAvatarUrl = user.getImg();
        }
    }

    @TableField(exist = false)
    private ProductFavorite productFavorite;

    public void selectProductFavorite(ProductFavoriteService service) {
        productFavorite = service.getOne(new LambdaQueryWrapper<ProductFavorite>().eq(ProductFavorite::getProductId, getId()).eq(ProductFavorite::getUserId, Utils.getUser().getId()));
    }
}
