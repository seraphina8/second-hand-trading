package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.dto.SmsCodeVerifyDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.platform.entity.SysSmsCode;

import java.util.Map;

/**
 * <p>
 * 短信验证码 服务类
 * </p>
 *
 *
 */
public interface SysSmsCodeService extends IService<SysSmsCode> {
    Page<SysSmsCode> page(Map<String, Object> query, Integer pageNum, Integer pageSize);

    void verity(SmsCodeVerifyDTO smsCodeVerifyDTO);
}
