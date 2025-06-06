package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.ProductFavorite;
import com.project.platform.exception.CustomException;
import com.project.platform.mapper.ProductFavoriteMapper;
import com.project.platform.service.ProductFavoriteService;
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
 * 商品收藏 服务实现类
 * </p>
 *
 *
 */
@Service
public class ProductFavoriteServiceImpl extends ServiceImpl<ProductFavoriteMapper, ProductFavorite> implements ProductFavoriteService {

    @Resource
    private SysUserService sysUserService;


    @Resource
    private ProductService productService;

    @Override
    public boolean save(ProductFavorite entity) {
        entity.setUserId(Utils.getUser().getId());
        check(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(ProductFavorite entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(ProductFavorite entity) {
        LambdaQueryWrapper<ProductFavorite> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProductFavorite::getUserId, entity.getUserId());
        queryWrapper.eq(ProductFavorite::getProductId, entity.getProductId());
        queryWrapper.ne(entity.getId() != null, ProductFavorite::getId, entity.getId());
        if (getOne(queryWrapper) != null) {
            throw new CustomException("已经收藏过了");
        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<ProductFavorite>().in(ProductFavorite::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<ProductFavorite> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<ProductFavorite> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ProductFavorite::getId);
        Utils.buildQueryWrapper(query, "userName", x -> wrapper.in(ProductFavorite::getUserId, sysUserService.search(x)));
        Utils.buildQueryWrapper(query, "productName", x -> wrapper.in(ProductFavorite::getProductId, productService.search(x)));
        wrapper.eq(!Utils.getUser().isAdmin(), ProductFavorite::getUserId, Utils.getUser().getId());
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
