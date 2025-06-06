package com.project.platform.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.dto.EditPasswordDTO;
import com.project.platform.dto.UserLoginDTO;
import com.project.platform.dto.UserRegisterDTO;
import com.project.platform.dto.resetPasswordBySmsCodeDTO;
import com.project.platform.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.platform.vo.SysUserLoginVO;

import java.util.List;
import java.util.Map;
/**
 * <p>
 *  服务类
 * </p>
 *
 *
 *
 */
public interface SysUserService extends IService<SysUser> {
        Page<SysUser> page(Map<String, Object> query, Integer pageNum, Integer pageSize);

        void register(UserRegisterDTO userRegisterDTO);

        void editPassword(EditPasswordDTO dto);
        void resetPassword(resetPasswordBySmsCodeDTO dto);

        SysUserLoginVO login(UserLoginDTO userLoginDTO);

        SysUserLoginVO smsLogin(UserLoginDTO userLoginDTO);


        String resetPassword(Integer id);

        List<Integer> search(String keyword);
}
