package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.*;
import com.project.platform.mapper.ProductMapper;
import com.project.platform.service.ProductFavoriteService;
import com.project.platform.service.ProductHistoryService;
import com.project.platform.service.ProductService;
import com.project.platform.service.SysUserService;
import com.project.platform.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 *
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Resource
    private ProductFavoriteService productFavoriteService;

    @Resource
    private ProductHistoryService productHistoryService;


    @Resource
    private SysUserService sysUserService;
    @Override
    public boolean save(Product entity) {
        check(entity);
        entity.setUserId(Utils.getUser().getId());
        entity.setPageView(0);
        entity.setWant(0);
        entity.setStatus(1);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Product entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(Product entity) {
//        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Product::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, Product::getId, entity.getId());
//        if (exists(wrapper)) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<Product>().in(Product::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<Product> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Product::getId);
        wrapper.eq(query.get("status") != null, Product::getStatus, query.get("status"));
        wrapper.eq(query.get("productClassifyId") != null, Product::getProductClassifyId, query.get("productClassifyId"));
        wrapper.eq(query.get("productFinenessId") != null, Product::getProductFinenessId, query.get("productFinenessId"));
        wrapper.like(query.get("name") != null, Product::getName, query.get("name"));
        wrapper.like(query.get("content") != null, Product::getContent, query.get("content"));
        if (query.get("type") != null && query.get("type").toString().equals("my")) {
            wrapper.eq(!Utils.getUser().isAdmin(), Product::getUserId, Utils.getUser().getId());
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }


    @Override
    public void view(Integer id) {
        Product product = getById(id);
        product.setPageView(product.getPageView() + 1);
        super.updateById(product);
    }

    @Override
    public void want(Integer id) {
        Product product = getById(id);
        product.setWant(product.getWant() + 1);
        super.updateById(product);
    }


    public List<Product> recommend(Integer size,Integer pageNumber) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Product::getCreateTime);
        List<Product> productList = list(wrapper);
        List<ProductHistory> productHistoryList = productHistoryService.list(new LambdaQueryWrapper<ProductHistory>().eq(ProductHistory::getUserId, Utils.getUser().getId()));
        for (ProductHistory productHistory : productHistoryList) {
            productHistory.selectProduct(productList);
        }
        List<ProductFavorite> productFavoritesList = productFavoriteService.list(new LambdaQueryWrapper<ProductFavorite>().eq(ProductFavorite::getUserId, Utils.getUser().getId()));
        for (ProductFavorite productFavorite : productFavoritesList) {
            productFavorite.selectProduct(productList);
        }
        productList = productList.stream().filter(item -> item.getStatus() == 1).collect(Collectors.toList());
        for (Product product : productList) {
            for (ProductHistory item : productHistoryList) {
                if (item.getProduct() == null) {
                    continue;
                }
                if (item.getProduct().getProductClassifyId().equals(product.getProductClassifyId())) {
                    product.setWeight(product.getWeight() + 1);
                }
            }
            for (ProductFavorite item : productFavoritesList) {
                if (item.getProduct() == null) {
                    continue;
                }
                if (item.getProduct().getProductClassifyId().equals(product.getProductClassifyId())) {
                    product.setWeight(product.getWeight() + 10);
                }
            }

        }
        int startIndex = (pageNumber-1)* size;
        return productList.stream()
                .sorted(Comparator.comparing(Product::getWeight).reversed())
                .skip(startIndex)
                .limit(size)
                .collect(Collectors.toList());
    }



    public List<Integer> search(String keyword) {
        List<Integer> result = new ArrayList<>();
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Product::getName, keyword);
        List<Product> list = list(wrapper);
        if (list.size() == 0) {
            result.add(0);
        } else {
            result=list.stream().map(Product::getId).collect(Collectors.toList());
        }
        return result;
    }
}
