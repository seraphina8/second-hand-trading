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
 * 公告
 * </p>
 *
 *
 */
@Getter
@Setter
@TableName("sys_inform")
@ApiModel(value = "SysInform对象", description = "公告")
public class SysInform extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("标题")
    private String name;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("置顶")
    private String winTop;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("发布人")
    private Integer userId;

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
