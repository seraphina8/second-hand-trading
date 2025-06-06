package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 *  服务类
 * </p>
 *
 *
 *
 */
public interface SysRoleService extends IService<SysRole> {
        Page<SysRole> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
