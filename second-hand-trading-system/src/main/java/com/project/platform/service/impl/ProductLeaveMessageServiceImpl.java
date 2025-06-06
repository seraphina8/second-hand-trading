package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.ProductLeaveMessage;
import com.project.platform.mapper.ProductLeaveMessageMapper;
import com.project.platform.service.ProductLeaveMessageService;
import com.project.platform.service.ProductService;
import com.project.platform.service.SysUserService;
import com.project.platform.utils.UserThreadLocal;
import com.project.platform.utils.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品留言 服务实现类
 * </p>
 *
 *
 */
@Service
public class ProductLeaveMessageServiceImpl extends ServiceImpl<ProductLeaveMessageMapper, ProductLeaveMessage> implements ProductLeaveMessageService {
    @Resource
    private SysUserService sysUserService;


    @Resource
    private ProductService productService;

    @Override
    public boolean save(ProductLeaveMessage entity) {
        check(entity);
        entity.setUserId(Utils.getUser().getId());
        return super.save(entity);
    }

    @Override
    public boolean updateById(ProductLeaveMessage entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(ProductLeaveMessage entity) {
//        LambdaQueryWrapper<ProductLeaveMessage> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(ProductLeaveMessage::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, ProductLeaveMessage::getId, entity.getId());
//        if (exists(wrapper)) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<ProductLeaveMessage>().in(ProductLeaveMessage::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<ProductLeaveMessage> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<ProductLeaveMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(ProductLeaveMessage::getId);
        Utils.buildQueryWrapper(query, "content", x -> wrapper.in(ProductLeaveMessage::getContent, x));
        Utils.buildQueryWrapper(query, "userName", x -> wrapper.in(ProductLeaveMessage::getUserId, sysUserService.search(x)));
        if (query.get("productId") == null) {
            Utils.buildQueryWrapper(query, "productName", x -> wrapper.in(ProductLeaveMessage::getProductId, productService.search(x)));
        }
        if (query.get("productId") == null) {
            Utils.buildQueryWrapper(query, "productName", x -> wrapper.in(ProductLeaveMessage::getProductId, productService.search(x)));
        }
        wrapper.eq(query.get("productId") != null, ProductLeaveMessage::getProductId, query.get("productId"));
        wrapper.eq(query.get("userId") != null, ProductLeaveMessage::getUserId, query.get("userId"));

        if(UserThreadLocal.getCurrentUser().getRoleId()==2){
            wrapper.eq(ProductLeaveMessage::getUserId,UserThreadLocal.getCurrentUser().getId());
        }

        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
