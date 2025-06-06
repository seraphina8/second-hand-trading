package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 *
 *
 */
public interface SysMenuService extends IService<SysMenu> {
    Page<SysMenu> page(Map<String, Object> query, Integer pageNum, Integer pageSize);

    List<SysMenu> tree(Map<String, Object> query);

    List<SysMenu> tree(List<SysMenu> list,Integer parentId);
}
