package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.ProductFineness;
import com.project.platform.exception.CustomException;
import com.project.platform.mapper.ProductFinenessMapper;
import com.project.platform.service.ProductFinenessService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品成色 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class ProductFinenessServiceImpl extends ServiceImpl<ProductFinenessMapper, ProductFineness> implements ProductFinenessService{

    @Override
    public boolean save(ProductFineness entity) {
        check(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(ProductFineness entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(ProductFineness entity) {
        LambdaQueryWrapper<ProductFineness> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductFineness::getName, entity.getName());
        wrapper.ne(entity.getId() != null, ProductFineness::getId, entity.getId());
        if (exists(wrapper)) {
            throw new CustomException("名称重复");
        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<ProductFineness>().in(ProductFineness::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<ProductFineness> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<ProductFineness> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ProductFineness::getId);
        wrapper.like(query.get("name") != null, ProductFineness::getName, query.get("name"));
        wrapper.like(query.get("remark") != null, ProductFineness::getRemark, query.get("remark"));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
