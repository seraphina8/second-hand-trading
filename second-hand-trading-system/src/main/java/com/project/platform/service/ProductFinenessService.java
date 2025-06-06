package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.ProductFineness;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 商品成色 服务类
 * </p>
 *
 *
 *
 */
public interface ProductFinenessService extends IService<ProductFineness> {
        Page<ProductFineness> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
