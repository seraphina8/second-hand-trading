package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.ChatMessage;
import com.project.platform.mapper.ChatMessageMapper;
import com.project.platform.service.ChatMessageService;
import com.project.platform.service.ChatRoomService;
import com.project.platform.service.SysUserService;
import com.project.platform.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 聊天消息 服务实现类
 * </p>
 *
 *
 */
@Service
public class ChatMessageServiceImpl extends ServiceImpl<ChatMessageMapper, ChatMessage> implements ChatMessageService {
    @Resource
    private ChatRoomService chatRoomService;
    @Resource
    private SysUserService sysUserService;

    @Override
    public boolean save(ChatMessage entity) {
        check(entity);
        entity.setUserId(Utils.getUser().getId());
        entity.setStatus(2);
        entity.setCreateTime(LocalDateTime.now());
        boolean save = super.save(entity);
        chatRoomService.addMessage(entity);
        return save;
    }

    @Override
    public boolean updateById(ChatMessage entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(ChatMessage entity) {
//        LambdaQueryWrapper<ChatMessage> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(ChatMessage::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, ChatMessage::getId, entity.getId());
//        if (exists(wrapper)) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<ChatMessage>().in(ChatMessage::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<ChatMessage> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<ChatMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ChatMessage::getId);
        Utils.buildQueryWrapper(query, "chatRoomId", x -> wrapper.eq(ChatMessage::getChatRoomId, x));
        Utils.buildQueryWrapper(query, "lastId", x -> wrapper.lt(ChatMessage::getId, x));
        Utils.buildQueryWrapper(query, "userName", x -> wrapper.in(ChatMessage::getUserId, sysUserService.search(x)));
        Utils.buildQueryWrapper(query, "content", x -> wrapper.like(ChatMessage::getContent, x));
        Utils.buildQueryWrapper(query, "status", x -> wrapper.like(ChatMessage::getStatus, x));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }


    @Override
    public boolean read(Integer chatRoomId) {
        LambdaUpdateWrapper<ChatMessage> updateWrapper = Wrappers.<ChatMessage>lambdaUpdate().ne(ChatMessage::getUserId, Utils.getUser().getId())
                .eq(ChatMessage::getChatRoomId, chatRoomId)
                .eq(ChatMessage::getStatus, 2)
                .set(ChatMessage::getStatus, 1);
        return super.update(updateWrapper);
    }
}
