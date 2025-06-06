package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.ShippingAddress;
import com.project.platform.mapper.ShippingAddressMapper;
import com.project.platform.service.ShippingAddressService;
import com.project.platform.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收货地址 服务实现类
 * </p>
 *
 *
 */
@Service
public class ShippingAddressServiceImpl extends ServiceImpl<ShippingAddressMapper, ShippingAddress> implements ShippingAddressService {

    @Override
    public boolean save(ShippingAddress entity) {
        check(entity);
        entity.setUserId(Utils.getUser().getId());
        return super.save(entity);
    }

    @Override
    public boolean updateById(ShippingAddress entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(ShippingAddress entity) {
        if (1 == entity.getIsDefault()) {
            LambdaUpdateWrapper<ShippingAddress> updateWrapper = Wrappers.<ShippingAddress>lambdaUpdate().eq(ShippingAddress::getUserId, Utils.getUser().getId()).set(ShippingAddress::getIsDefault, 0);
            this.update(updateWrapper);
        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<ShippingAddress>().in(ShippingAddress::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<ShippingAddress> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<ShippingAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ShippingAddress::getId);
        wrapper.like(query.get("name") != null, ShippingAddress::getName, query.get("name"));
        wrapper.like(query.get("tel") != null, ShippingAddress::getTel, query.get("tel"));
        wrapper.like(query.get("address") != null, ShippingAddress::getAddress, query.get("address"));
        wrapper.eq(query.get("isDefault") != null, ShippingAddress::getIsDefault, query.get("isDefault"));
        wrapper.eq(!Utils.getUser().isAdmin(), ShippingAddress::getUserId, Utils.getUser().getId());
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
