package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.ProductClassify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 商品分类 服务类
 * </p>
 *
 *
 *
 */
public interface ProductClassifyService extends IService<ProductClassify> {
        Page<ProductClassify> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
