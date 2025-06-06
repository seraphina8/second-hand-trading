package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 *
 */
public interface ProductService extends IService<Product> {
    Page<Product> page(Map<String, Object> query, Integer pageNum, Integer pageSize);

    void view(Integer id);

    void want(Integer id);

    List<Product> recommend(Integer size,Integer pageNumber);

     List<Integer> search(String keyword);
}
