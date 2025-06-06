package com.project.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.dto.SmsCodeVerifyDTO;
import com.project.platform.entity.SysSmsCode;
import com.project.platform.exception.CustomException;
import com.project.platform.mapper.SysSmsCodeMapper;
import com.project.platform.service.SysSmsCodeService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * 短信验证码 服务实现类
 * </p>
 *
 *
 */
@Service
public class SysSmsCodeServiceImpl extends ServiceImpl<SysSmsCodeMapper, SysSmsCode> implements SysSmsCodeService {

    @Override
    public boolean save(SysSmsCode entity) {
        check(entity);
        int randomSixDigitNumber = ThreadLocalRandom.current().nextInt(100000, 1000000);
        entity.setCode(String.valueOf(randomSixDigitNumber));
        String content = "【管理系统】验证码%s，5分钟内有效，请勿泄漏";
        entity.setContent(String.format(content, randomSixDigitNumber));
        entity.setExpirationTime(LocalDateTime.now().plusMinutes(5));
        return super.save(entity);
    }

    @Override
    public boolean updateById(SysSmsCode entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(SysSmsCode entity) {
//        LambdaQueryWrapper<SysSmsCode> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(SysSmsCode::getName, entity.getName());
//        wrapper.ne(entity.getId() != null, SysSmsCode::getId, entity.getId());
//        if (count(wrapper) > 0) {
//            throw new CustomException("名称重复");
//        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<SysSmsCode>().in(SysSmsCode::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<SysSmsCode> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SysSmsCode> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(query.get("mobile") != null, SysSmsCode::getMobile, query.get("mobile"));
        wrapper.like(query.get("content") != null, SysSmsCode::getContent, query.get("content"));
        wrapper.eq(query.get("code") != null, SysSmsCode::getCode, query.get("code;"));
        wrapper.orderByDesc(SysSmsCode::getId);
        return page(new Page<>(pageNum, pageSize), wrapper);
    }


    public void verity(SmsCodeVerifyDTO smsCodeVerifyDTO) {
        LambdaQueryWrapper<SysSmsCode> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysSmsCode::getMobile, smsCodeVerifyDTO.getMobile());
        wrapper.gt(SysSmsCode::getExpirationTime, LocalDateTime.now());
        List<SysSmsCode> list = list(wrapper);
        if (ObjectUtils.isEmpty(list)) {
            throw new CustomException("短信验证码错误");
        }
        boolean flag=list.stream().filter(item -> item.getCode().equals(smsCodeVerifyDTO.getSmsCode())).findFirst().isPresent();
        if (!flag) {
            throw new CustomException("短信验证码错误");
        }
    }
}
