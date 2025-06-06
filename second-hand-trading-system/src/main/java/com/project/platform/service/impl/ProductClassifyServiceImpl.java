package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.ProductClassify;
import com.project.platform.exception.CustomException;
import com.project.platform.mapper.ProductClassifyMapper;
import com.project.platform.service.ProductClassifyService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品分类 服务实现类
 * </p>
 *
 *
 */
@Service
public class ProductClassifyServiceImpl extends ServiceImpl<ProductClassifyMapper, ProductClassify> implements ProductClassifyService {

    @Override
    public boolean save(ProductClassify entity) {
        check(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(ProductClassify entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(ProductClassify entity) {
        LambdaQueryWrapper<ProductClassify> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ProductClassify::getName, entity.getName());
        wrapper.ne(entity.getId() != null, ProductClassify::getId, entity.getId());
        if (exists(wrapper)) {
            throw new CustomException("名称重复");
        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<ProductClassify>().in(ProductClassify::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<ProductClassify> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<ProductClassify> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ProductClassify::getId);
        wrapper.like(query.get("name") != null, ProductClassify::getName, query.get("name"));
        wrapper.like(query.get("remark") != null, ProductClassify::getRemark, query.get("remark"));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
