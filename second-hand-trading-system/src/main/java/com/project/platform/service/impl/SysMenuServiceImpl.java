package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.SysMenu;
import com.project.platform.exception.CustomException;
import com.project.platform.mapper.SysMenuMapper;
import com.project.platform.service.SysMenuService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 *
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public boolean save(SysMenu entity) {
        entity.setCreateTime(LocalDateTime.now());
        check(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(SysMenu entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(SysMenu entity) {
        if (entity.getParentId().equals(entity.getId())) {
            throw new CustomException("上级菜单不能为自己");
        }
    }

    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<Menu>().in(Menu::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<SysMenu> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(SysMenu::getId);
        if (ObjectUtils.isNotEmpty(query.get("name"))) {
            wrapper.like(SysMenu::getName, query.get("name"));
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    public List<SysMenu> tree(Map<String, Object> query) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(SysMenu::getId);
        if (ObjectUtils.isNotEmpty(query.get("name"))) {
            wrapper.like(SysMenu::getName, query.get("name"));
        }
        if (ObjectUtils.isNotEmpty(query.get("status"))) {
            wrapper.like(SysMenu::getStatus, query.get("status"));
        }
        return tree(list(wrapper), 0);
    }

    @Override
    public List<SysMenu> tree(List<SysMenu> list, Integer parentId) {
        if (list == null) {
            list = list();
        }
        List<SysMenu> topList = list.stream().filter(x -> x.getParentId().equals(parentId)).sorted(Comparator.comparing(SysMenu::getSort)).collect(Collectors.toList());
        List<SysMenu> finalList = list;
        topList.forEach(test -> buiTree(test, finalList));
        return topList;
    }

    private void buiTree(SysMenu sysMenu, List<SysMenu> list) {
        List<SysMenu> children = list.stream()
                .filter(test -> Objects.equals(test.getParentId(), sysMenu.getId()))
                .sorted(Comparator.comparing(SysMenu::getSort)).collect(Collectors.toList());
        if (children.size() == 0) {
            return;
        }
        sysMenu.setChildren(children);
        sysMenu.getChildren().forEach(test -> buiTree(test, list));
    }
}
