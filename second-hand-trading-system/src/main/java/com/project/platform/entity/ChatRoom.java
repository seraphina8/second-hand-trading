package com.project.platform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.platform.dto.ChatUserDTO;

import java.time.LocalDateTime;

import com.project.platform.service.ChatMessageService;
import com.project.platform.service.ProductService;
import com.project.platform.service.SysUserService;
import com.project.platform.utils.Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 聊天房间
 * </p>
 *
 *
 */
@Getter
@Setter
@TableName("chat_room")
@ApiModel(value = "ChatRoom对象", description = "聊天房间")
public class ChatRoom extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名称")
    private String name;


    @ApiModelProperty("用户1Id")
    private Integer user1Id;

    @ApiModelProperty("用户2Id")
    private Integer user2Id;

    @ApiModelProperty("产品")
    private Integer productId;


    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @TableField(exist = false)
    private long unreadCount;


    @ApiModelProperty("图片")
    @TableField(exist = false)
    private String avatar;



    @TableField(exist = false)
    private Product product;

    public void selectAvatars(SysUserService sysUserService) {
        Integer userId = Utils.getUser().getId() == this.user1Id ? this.user2Id : this.user1Id;
        SysUser sysUser = sysUserService.getById(userId);
        if (sysUser == null) {
            return;
        }
        this.avatar = sysUser.getImg();
    }


    @TableField(exist = false)
    private ChatUserDTO user1;
    @TableField(exist = false)
    private ChatUserDTO user2;

    @TableField(exist = false)
    private ChatMessage lastMessage;


    public void selectUnreadCount(ChatMessageService chatMessageService) {
        Integer userId = Utils.getUser().getId() == this.user1Id ? this.user2Id : this.user1Id;
        LambdaQueryWrapper<ChatMessage> query = new LambdaQueryWrapper<>();
        query.eq(ChatMessage::getChatRoomId, getId());
        query.eq(ChatMessage::getStatus, 0);
        query.eq(ChatMessage::getUserId, userId);
        this.unreadCount = chatMessageService.count(query);
    }

    public void selectUser1Info(SysUserService sysUserService) {
        SysUser sysUser = sysUserService.getById(user1Id);
        if (sysUser == null) {
            return;
        }
        ChatUserDTO chatUserDTO = new ChatUserDTO();
        chatUserDTO.setId(sysUser.getId());
        chatUserDTO.setUserName(sysUser.getUsername());
        chatUserDTO.setAvatar(sysUser.getImg());
        this.user1 = chatUserDTO;
    }

    public void selectUser2Info(SysUserService sysUserService) {
        SysUser sysUser = sysUserService.getById(user2Id);
        if (sysUser == null) {
            return;
        }
        ChatUserDTO chatUserDTO = new ChatUserDTO();
        chatUserDTO.setId(sysUser.getId());
        chatUserDTO.setUserName(sysUser.getUsername());
        chatUserDTO.setAvatar(sysUser.getImg());
        this.user2 = chatUserDTO;
    }


    public void selectLastMessage(ChatMessageService chatMessageService, SysUserService sysUserService) {
        LambdaQueryWrapper<ChatMessage> query = new LambdaQueryWrapper<>();
        query.eq(ChatMessage::getChatRoomId, getId());
        query.orderByDesc(ChatMessage::getCreateTime);
        ChatMessage chatMessages = chatMessageService.getOne(query, false);
        if (chatMessages == null) {
            return;
        }
        this.lastMessage = chatMessages;
        this.lastMessage.selectUserInfo(sysUserService);
    }


    public void selectProduct(ProductService productService) {
        this.product= productService.getById(productId);
    }

}
