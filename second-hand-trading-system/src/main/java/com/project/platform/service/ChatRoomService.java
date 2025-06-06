package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.dto.MessagesNotifyDTO;
import com.project.platform.entity.ChatMessage;
import com.project.platform.entity.ChatRoom;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
/**
 * <p>
 * 聊天房间 服务类
 * </p>
 *
 *
 *
 */
public interface ChatRoomService extends IService<ChatRoom> {
        Page<ChatRoom> page(Map<String, Object> query, Integer pageNum, Integer pageSize);

        List<MessagesNotifyDTO> getNewMessage() ;


         void addMessage(ChatMessage chatMessage);
}
