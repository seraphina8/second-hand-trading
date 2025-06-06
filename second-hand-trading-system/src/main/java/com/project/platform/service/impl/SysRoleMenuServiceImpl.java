package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.entity.SysRoleMenu;
import com.project.platform.mapper.SysRoleMenuMapper;
import com.project.platform.service.SysRoleMenuService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 *
 *
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {


    @Override
    public boolean save(Integer roleId, List<Integer> menuIds) {
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysRoleMenu::getRoleId, roleId);
        remove(wrapper);
        List<SysRoleMenu> sysRoleMenus = menuIds.stream().map(menuId -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            return sysRoleMenu;
        }).collect(Collectors.toList());
        return saveBatch(sysRoleMenus);
    }

    @Override
    public boolean updateById(SysRoleMenu entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(SysRoleMenu entity) {
//        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(RoleMenu::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, RoleMenu::getId, entity.getId());
//        if (count(wrapper) > 0) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<RoleMenu>().in(RoleMenu::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<SysRoleMenu> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SysRoleMenu> wrapper = new LambdaQueryWrapper<>();

        if (ObjectUtils.isNotEmpty(query.get("name"))) {
           // wrapper.like(RoleMenu::getName, query.get("name"));
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }


}
