package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.ProductHistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 商品历史 服务类
 * </p>
 *
 *
 *
 */
public interface ProductHistoryService extends IService<ProductHistory> {
        Page<ProductHistory> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
