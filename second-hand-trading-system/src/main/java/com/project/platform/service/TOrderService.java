package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 订单 服务类
 * </p>
 *
 *
 *
 */
public interface TOrderService extends IService<TOrder> {
        Page<TOrder> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
        boolean deliver(TOrder entity);

        boolean receive(Integer id);

         boolean pay(Integer id);
}
