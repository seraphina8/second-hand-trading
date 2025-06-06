package com.project.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.project.platform.service.ProductService;
import com.project.platform.service.SysUserService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品历史
 * </p>
 *
 *
 *
 */
@Getter
@Setter
@TableName("product_history")
@ApiModel(value = "ProductHistory对象", description = "商品历史")
public class ProductHistory extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户")
    private Integer userId;

    @ApiModelProperty("商品")
    private Integer productId;

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

    @TableField(exist = false)
    private Product product;
    public void selectProduct(ProductService service) {
        product = service.getById(this.productId);
    }

    public void selectProduct(List<Product> list){
        Optional<Product> first = list.stream().filter(item -> item.getId().equals(productId)).findFirst();
        if (!first.isPresent()){
            return;
        }
        product =first.get();
    }

}
