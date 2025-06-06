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
 * 订单
 * </p>
 *
 *
 */
@Getter
@Setter
@TableName("t_order")
@ApiModel(value = "TOrder对象", description = "订单")
public class TOrder extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户")
    private Integer userId;

    @ApiModelProperty("卖家")
    private Integer sellerId;

    @ApiModelProperty("商品")
    private Integer productId;

    @ApiModelProperty("价格")
    private Double price;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("发货方式")
    private Integer mode;

    @ApiModelProperty("收货地址")
    private String shippingAddress;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("付款交易号")
    private String payTransactionNumber;

    @ApiModelProperty("订单编号")
    private String number;

    @ApiModelProperty("物流单号")
    private String trackingNumber;

    @ApiModelProperty("下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("付款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;

    @ApiModelProperty("发货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliverTime;

    @ApiModelProperty("成交时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime successTime;


    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String sellerName;





    public void selectUserName(SysUserService service) {
        SysUser user = service.getById(this.userId);
        if (user == null) {
            return;
        }
        this.userName = user.getUsername();
    }

    public void selectSellerName(SysUserService service) {
        SysUser user = service.getById(this.sellerId);
        if (user == null) {
            return;
        }
        this.sellerName = user.getUsername();
    }

    @TableField(exist = false)
    private Product product;
    public void selectSellerName(ProductService service) {
        product = service.getById(this.productId);
    }




}
