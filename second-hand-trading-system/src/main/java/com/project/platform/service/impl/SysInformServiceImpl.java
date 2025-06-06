package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.SysInform;
import com.project.platform.mapper.SysInformMapper;
import com.project.platform.service.SysInformService;
import com.project.platform.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公告 服务实现类
 * </p>
 *
 *
 */
@Service
public class SysInformServiceImpl extends ServiceImpl<SysInformMapper, SysInform> implements SysInformService {

    @Override
    public boolean save(SysInform entity) {
        check(entity);
        entity.setUserId(Utils.getUser().getId());
        return super.save(entity);
    }

    @Override
    public boolean updateById(SysInform entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(SysInform entity) {
//        LambdaQueryWrapper<SysInform> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(SysInform::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, SysInform::getId, entity.getId());
//        if (count(wrapper) > 0) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<SysInform>().in(SysInform::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<SysInform> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SysInform> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(query.get("name") != null, SysInform::getName, query.get("name"));
        wrapper.like(query.get("content") != null, SysInform::getContent, query.get("content"));
        wrapper.eq(query.get("winTop") != null, SysInform::getWinTop, query.get("winTop"));
        wrapper.eq(query.get("status") != null, SysInform::getStatus, query.get("status"));
        wrapper.orderByDesc(SysInform::getId);
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
