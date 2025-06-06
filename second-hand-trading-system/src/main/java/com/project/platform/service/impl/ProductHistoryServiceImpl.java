package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.ProductHistory;
import com.project.platform.mapper.ProductHistoryMapper;
import com.project.platform.service.ProductHistoryService;
import com.project.platform.service.ProductService;
import com.project.platform.service.SysUserService;
import com.project.platform.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品历史 服务实现类
 * </p>
 *
 *
 */
@Service
public class ProductHistoryServiceImpl extends ServiceImpl<ProductHistoryMapper, ProductHistory> implements ProductHistoryService {


    @Resource
    private ProductService productService;

    @Resource
    private SysUserService sysUserService;

    @Override
    public boolean save(ProductHistory entity) {
        entity.setUserId(Utils.getUser().getId());
        check(entity);
        productService.view(entity.getProductId());
        return super.save(entity);
    }

    @Override
    public boolean updateById(ProductHistory entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(ProductHistory entity) {
//        LambdaQueryWrapper<ProductHistory> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(ProductHistory::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, ProductHistory::getId, entity.getId());
//        if (exists(wrapper)) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<ProductHistory>().in(ProductHistory::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<ProductHistory> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<ProductHistory> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ProductHistory::getId);
        Utils.buildQueryWrapper(query, "userName", x -> wrapper.in(ProductHistory::getUserId, sysUserService.search(x)));
        Utils.buildQueryWrapper(query, "productName", x -> wrapper.in(ProductHistory::getProductId, productService.search(x)));
        wrapper.eq(!Utils.getUser().isAdmin(), ProductHistory::getUserId, Utils.getUser().getId());
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
