package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
/**
 * <p>
 * 角色菜单 服务类
 * </p>
 *
 *
 *
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
        Page<SysRoleMenu> page(Map<String, Object> query, Integer pageNum, Integer pageSize);


        boolean save(Integer roleId,List<Integer> menuIds);
}
