package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.ProductLeaveMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 商品留言 服务类
 * </p>
 *
 *
 *
 */
public interface ProductLeaveMessageService extends IService<ProductLeaveMessage> {
        Page<ProductLeaveMessage> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
