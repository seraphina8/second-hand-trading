package com.project.platform.service.impl;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.Product;
import com.project.platform.entity.TOrder;
import com.project.platform.exception.CustomException;
import com.project.platform.mapper.TOrderMapper;
import com.project.platform.service.ProductService;
import com.project.platform.service.SysUserService;
import com.project.platform.service.TOrderService;
import com.project.platform.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 *
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {
    @Resource
    private ProductService productService;

    @Resource
    private NoNumberService noNumberService;


    @Resource
    private SysUserService sysUserService;

    @Override
    public boolean save(TOrder entity) {
        check(entity);
        entity.setUserId(Utils.getUser().getId());
        Product product = productService.getById(entity.getProductId());
        if (product.getUserId().equals(Utils.getUser().getId())) {
            throw new CustomException("不能购买自己的商品");
        }
        product.setStatus(3);
        productService.updateById(product);
        entity.setSellerId(product.getUserId());
        entity.setPrice(product.getPrice());
        entity.setStatus(1);
        entity.setNumber(noNumberService.generateOrderNumber());
        entity.setCreateTime(LocalDateTime.now());
        return super.save(entity);
    }

    @Override
    public boolean updateById(TOrder entity) {
        check(entity);
        return super.updateById(entity);
    }


    @Override
    public boolean pay(Integer id) {
        TOrder order = getById(id);
        order.setStatus(2);
        order.setPayTransactionNumber(noNumberService.generatePayNumber());
        order.setPayTime(LocalDateTime.now());
        return super.updateById(order);
    }


    @Override
    public boolean deliver(TOrder entity) {
        TOrder order = getById(entity.getId());
        order.setStatus(3);
        order.setTrackingNumber(entity.getTrackingNumber());
        order.setDeliverTime(LocalDateTime.now());
        return super.updateById(order);
    }

    @Override
    public boolean receive(Integer id) {
        TOrder order = getById(id);
        order.setStatus(4);
        order.setSuccessTime(LocalDateTime.now());
        return super.updateById(order);
    }


    private void check(TOrder entity) {
//        LambdaQueryWrapper<TOrder> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(TOrder::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, TOrder::getId, entity.getId());
//        if (exists(wrapper)) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<TOrder>().in(TOrder::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<TOrder> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<TOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(TOrder::getId);
        Utils.buildQueryWrapper(query, "userName", x -> wrapper.in(TOrder::getUserId, sysUserService.search(x)));
        Utils.buildQueryWrapper(query, "sellerName", x -> wrapper.in(TOrder::getSellerId, sysUserService.search(x)));
        Utils.buildQueryWrapper(query, "productName", x -> wrapper.in(TOrder::getProductId, productService.search(x)));
        wrapper.like(query.get("shippingAddress") != null, TOrder::getShippingAddress, query.get("shippingAddress"));
        wrapper.eq(query.get("status") != null, TOrder::getStatus, query.get("status"));
        wrapper.eq(query.get("mode") != null, TOrder::getMode, query.get("mode"));
        wrapper.like(query.get("shippingAddress") != null, TOrder::getShippingAddress, query.get("shippingAddress"));
        wrapper.like(query.get("remark") != null, TOrder::getRemark, query.get("remark"));
        wrapper.eq(query.get("payTransactionNumber") != null, TOrder::getPayTransactionNumber, query.get("payTransactionNumber"));
        wrapper.eq(query.get("number") != null, TOrder::getNumber, query.get("number"));
        if (query.get("type") != null && query.get("type").toString().equals("in")) {
            wrapper.eq(TOrder::getUserId, Utils.getUser().getId());
        } else {
            wrapper.eq(!Utils.getUser().isAdmin(), TOrder::getSellerId, Utils.getUser().getId());
        }
        wrapper.eq(query.get("userId") != null, TOrder::getUserId, query.get("userId"));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
