package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.ChatMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 聊天消息 服务类
 * </p>
 *
 *
 */
public interface ChatMessageService extends IService<ChatMessage> {
    Page<ChatMessage> page(Map<String, Object> query, Integer pageNum, Integer pageSize);

    boolean read(Integer chatRoomId);
}
