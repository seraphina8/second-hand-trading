package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.ShippingAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 收货地址 服务类
 * </p>
 *
 *
 *
 */
public interface ShippingAddressService extends IService<ShippingAddress> {
        Page<ShippingAddress> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
