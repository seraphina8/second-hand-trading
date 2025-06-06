package com.project.platform.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.common.PageVO;
import com.project.platform.common.Result;
import com.project.platform.dto.MessagesNotifyDTO;
import com.project.platform.entity.ChatRoom;
import com.project.platform.service.ChatMessageService;
import com.project.platform.service.ChatRoomService;
import com.project.platform.service.ProductService;
import com.project.platform.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 聊天房间 前端控制器
 * </p>
 *
 *
 */
@RestController
@RequestMapping("/chatRoom")
public class ChatRoomController {
    @Resource
    private ChatRoomService service;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private ChatMessageService chatMessageService;

    @Resource
    private ProductService productService;



    /**
     * 列表
     */
    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Result<List<ChatRoom>> list() {
        return new Result<>().success(service.list());
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<PageVO<ChatRoom>> findPage(@RequestParam Map<String, Object> query, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<ChatRoom> page = service.page(query, pageNum, pageSize);
        page.getRecords().forEach(chatRoom -> {
            chatRoom.selectAvatars(sysUserService);
            chatRoom.selectUser1Info(sysUserService);
            chatRoom.selectUser2Info(sysUserService);
            chatRoom.selectLastMessage(chatMessageService, sysUserService);
            chatRoom.selectUnreadCount(chatMessageService);
            chatRoom.selectProduct(productService);
        });
        return new Result<>().success(new PageVO<>(page));
    }

    /**
     * 数据新增
     */
    @ApiOperation(value = "数据新增", notes = "数据新增")
    @PostMapping()
    public Result add(@Validated @RequestBody ChatRoom entity) {
        service.save(entity);
        return new Result<>().success(entity);
    }

    /**
     * 数据更新
     */
    @ApiOperation(value = "数据更新", notes = "数据更新")
    @PutMapping()
    public Result update(@Validated @RequestBody ChatRoom entity) {
        service.updateById(entity);
        return new Result<>().success();
    }

    /**
     * 根据id删除
     */
    @ApiOperation(value = "数据根据id批量删除", notes = "数据根据id批量删除")
    @DeleteMapping()
    public Result delBatch(@RequestBody List<Integer> ids) {
        service.removeByIds(ids);
        return new Result<>().success();
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping("/{id}")
    public Result<ChatRoom> getById(@PathVariable("id") Integer id) {
        ChatRoom entity = service.getById(id);
        return new Result<>().success(entity);
    }


    @GetMapping("/newMessage")
    public Result<List<MessagesNotifyDTO>> getNewMessage() {
        return new Result<>().success(service.getNewMessage());
    }
}
