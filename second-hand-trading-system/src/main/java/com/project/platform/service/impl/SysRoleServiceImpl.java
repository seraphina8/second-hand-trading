package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.SysRole;
import com.project.platform.mapper.SysRoleMapper;
import com.project.platform.service.SysRoleMenuService;
import com.project.platform.service.SysRoleService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public boolean save(SysRole entity) {
        check(entity);
        saveMenu(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(SysRole entity) {
        check(entity);
        saveMenu(entity);
        return super.updateById(entity);
    }

    private void saveMenu(SysRole entity) {
        sysRoleMenuService.save(entity.getId(), entity.getMenuIds());
    }

    private void check(SysRole entity) {
//        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(SysRole::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, SysRole::getId, entity.getId());
//        if (count(wrapper) > 0) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<SysRole>().in(SysRole::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<SysRole> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(SysRole::getId);
        if (ObjectUtils.isNotEmpty(query.get("name"))) {
            wrapper.like(SysRole::getName, query.get("name"));
        }
        if (ObjectUtils.isNotEmpty(query.get("content"))) {
            wrapper.like(SysRole::getCode, query.get("content"));
        }
        if (ObjectUtils.isNotEmpty(query.get("code"))) {
            wrapper.like(SysRole::getCode, query.get("code"));
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }
}
