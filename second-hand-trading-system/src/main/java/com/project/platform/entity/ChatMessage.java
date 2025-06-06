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
 * 聊天消息
 * </p>
 *
 *
 *
 */
@Getter
@Setter
@TableName("chat_message")
@ApiModel(value = "ChatMessage对象", description = "聊天消息")
public class ChatMessage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户Id")
    private Integer userId;

    @ApiModelProperty("聊天房间Id")
    private Integer chatRoomId;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private String userName;

    public void selectUserInfo(SysUserService sysUserService) {
        SysUser sysUser = sysUserService.getById(userId);
        if (sysUser == null) {
            return;
        }
        this.avatar = sysUser.getImg();
        this.userName = sysUser.getUsername();
    }

}
