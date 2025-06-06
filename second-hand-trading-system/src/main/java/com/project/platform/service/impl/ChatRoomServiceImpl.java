package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.project.platform.dto.MessagesNotifyDTO;
import com.project.platform.dto.SysUserDTO;
import com.project.platform.entity.ChatMessage;
import com.project.platform.entity.ChatRoom;
import com.project.platform.exception.CustomException;
import com.project.platform.mapper.ChatRoomMapper;
import com.project.platform.service.ChatMessageService;
import com.project.platform.service.ChatRoomService;
import com.project.platform.service.ProductService;
import com.project.platform.service.SysUserService;
import com.project.platform.utils.UserThreadLocal;
import com.project.platform.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <p>
 * 聊天房间 服务实现类
 * </p>
 *
 * 
 */
@Service
public class ChatRoomServiceImpl extends ServiceImpl<ChatRoomMapper, ChatRoom> implements ChatRoomService {


    private static final Map<Integer, Queue<MessagesNotifyDTO>> messagesNotifyMap = new ConcurrentHashMap<>();
    @Resource
    private ProductService productService;


    @Resource
    private SysUserService sysUserService;
    @Resource
    private ChatMessageService chatMessageService;

    @Override
    public boolean save(ChatRoom entity) {
        entity.setUser1Id(Utils.getUser().getId());
        check(entity);
        LambdaQueryWrapper<ChatRoom> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatRoom::getProductId, entity.getProductId());
        wrapper.eq(ChatRoom::getUser1Id, entity.getUser1Id());
        ChatRoom chatRoom = getOne(wrapper);
        if (chatRoom != null) {
            entity.setId(chatRoom.getId());
            return true;
        }
        entity.setUser2Id(productService.getById(entity.getProductId()).getUserId());
        if (entity.getUser2Id().equals(Utils.getUser().getId())) {
            throw new CustomException("不能和自己聊天");
        }
        productService.want(entity.getProductId());
        boolean save = super.save(entity);
        entity.selectAvatars(sysUserService);
        entity.selectUser1Info(sysUserService);
        entity.selectUser2Info(sysUserService);
        entity.selectLastMessage(chatMessageService, sysUserService);
        entity.selectUnreadCount(chatMessageService);
        entity.selectProduct(productService);
        addMessage(entity);
        return save;
    }


    @Override
    public boolean updateById(ChatRoom entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(ChatRoom entity) {
//        LambdaQueryWrapper<ChatRoom> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(ChatRoom::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, ChatRoom::getId, entity.getId());
//        if (exists(wrapper)) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<ChatRoom>().in(ChatRoom::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<ChatRoom> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {

        LambdaQueryWrapper<ChatRoom> wrapper = new LambdaQueryWrapper<>();
        SysUserDTO currentUser = UserThreadLocal.getCurrentUser();
        if (!currentUser.isAdmin()){
            wrapper.and(wrapper1 ->
                    wrapper1.eq(ChatRoom::getUser1Id, currentUser.getId()).or().eq(ChatRoom::getUser2Id, currentUser.getId()));
        }
        wrapper.orderByDesc(ChatRoom::getUpdateTime);
        Utils.buildQueryWrapper(query, "user1Name", x -> wrapper.in(ChatRoom::getUser1Id, sysUserService.search(x)));
        Utils.buildQueryWrapper(query, "user2Name", x -> wrapper.in(ChatRoom::getUser2Id, sysUserService.search(x)));
        Utils.buildQueryWrapper(query, "productName", x -> wrapper.in(ChatRoom::getProductId, productService.search(x)));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }


    public List<MessagesNotifyDTO> getNewMessage() {
        Queue<MessagesNotifyDTO> messages = getUserMessageQueue(Utils.getUser().getId());
        // 检查是否有现成的消息
        List<MessagesNotifyDTO> message = new ArrayList<>();
        while (messages.peek() != null) {
            message.add(messages.poll());
        }
        if (message.size() > 0) {
            return message;
        } else {
            try {
                synchronized (messages) {
                    messages.wait(5000); // 等待新消息
                    while (messages.peek() != null) {
                        message.add(messages.poll());
                    }
                    return message;
                }
            } catch (InterruptedException e) {
                return null;
            }
        }
    }

    public void addMessage(ChatRoom chatRoom) {
        addMessage(chatRoom, chatRoom.getUser1Id());
        addMessage(chatRoom, chatRoom.getUser2Id());
    }

    private void addMessage(ChatRoom chatRoom, Integer userId) {
        MessagesNotifyDTO messagesNotifyDTO = new MessagesNotifyDTO();
        messagesNotifyDTO.setType(1);
        messagesNotifyDTO.setData(chatRoom);
        Queue<MessagesNotifyDTO> messages = getUserMessageQueue(userId);
        synchronized (messages) {
            messages.add(messagesNotifyDTO);
            messages.notifyAll(); // 通知等待的线程
        }
    }


    public void addMessage(ChatMessage chatMessage) {
        ChatRoom chatRoom = getById(chatMessage.getChatRoomId());
        chatRoom.setUpdateTime(LocalDateTime.now());
        updateById(chatRoom);
        addMessage(chatMessage, chatRoom.getUser1Id());
        addMessage(chatMessage, chatRoom.getUser2Id());
    }

    private void addMessage(ChatMessage chatMessage, Integer userId) {
        chatMessage.selectUserInfo(sysUserService);
        MessagesNotifyDTO messagesNotifyDTO = new MessagesNotifyDTO();
        messagesNotifyDTO.setType(2);
        messagesNotifyDTO.setData(chatMessage);
        Queue<MessagesNotifyDTO> messages = getUserMessageQueue(userId);
        synchronized (messages) {
            messages.add(messagesNotifyDTO);
            messages.notifyAll(); // 通知等待的线程
        }
    }

    private Queue<MessagesNotifyDTO> getUserMessageQueue(Integer userId) {
        if (!messagesNotifyMap.containsKey(userId)) {
            Queue<MessagesNotifyDTO> queueList = new ConcurrentLinkedQueue<>();
            messagesNotifyMap.put(userId, queueList);
        }
        return this.messagesNotifyMap.get(userId);
    }
}
