package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.entity.SysInform;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;
/**
 * <p>
 * 公告 服务类
 * </p>
 *
 *
 *
 */
public interface SysInformService extends IService<SysInform> {
        Page<SysInform> page(Map<String, Object> query, Integer pageNum, Integer pageSize);
}
