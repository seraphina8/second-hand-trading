package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.ProductFavorite;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 商品收藏 服务类
 * </p>
 *
 *
 *
 */
public interface ProductFavoriteService extends IService<ProductFavorite> {
        Page<ProductFavorite> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
