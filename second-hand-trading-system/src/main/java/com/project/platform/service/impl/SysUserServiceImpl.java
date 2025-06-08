package com.project.platform.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.project.platform.dto.*;
import com.project.platform.entity.SysMenu;
import com.project.platform.entity.SysRoleMenu;
import com.project.platform.entity.SysUser;
import com.project.platform.enums.StateType;
import com.project.platform.exception.CustomException;
import com.project.platform.mapper.SysUserMapper;
import com.project.platform.service.*;
import com.project.platform.utils.JwtUtils;
import com.project.platform.utils.UserThreadLocal;
import com.project.platform.utils.Utils;
import com.project.platform.vo.SysUserLoginVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 *
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {


    @Value("${resetPassword}")
    private String resetPassword;

    @Resource
    private SysSmsCodeService sysSmsCodeService;
    @Resource
    private SysMenuService sysMenuService;
    @Resource
    private SysRoleService sysRoleService;
    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public boolean save(SysUser entity) {
        check(entity);
        handlePassword(entity, entity.getPassword());
        return super.save(entity);
    }

    @Override
    public boolean updateById(SysUser entity) {
        check(entity);
        return super.updateById(entity);
    }

    private void check(SysUser entity) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, entity.getUsername());
        wrapper.ne(entity.getId() != null, SysUser::getId, entity.getId());
        if (exists(wrapper)) {
            throw new CustomException("用户名已存在");
        }
        LambdaQueryWrapper<SysUser> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(SysUser::getMobile, entity.getMobile());
        wrapper1.ne(entity.getId() != null, SysUser::getId, entity.getId());
        if (exists(wrapper1)) {
            throw new CustomException("手机号已存在");
        }
    }


    @Override
    public boolean removeByIds(Collection<?> list) {
        List<Integer> integerList = (List<Integer>) list;
//        list(new LambdaQueryWrapper<SysUser>().in(SysUser::getId, integerList)).forEach(test -> {
//            if (true) {
//                throw new CustomException("删除失败");
//            }
//        });
        return super.removeByIds(integerList);
    }

    @Override
    public Page<SysUser> page(Map<String, Object> query, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(query.get("mobile") != null, SysUser::getMobile, query.get("mobile"));
        wrapper.like(query.get("username") != null, SysUser::getUsername, query.get("username"));
        wrapper.like(query.get("email") != null, SysUser::getEmail, query.get("email"));
        wrapper.eq(query.get("sex") != null, SysUser::getSex, query.get("sex"));
        wrapper.eq(query.get("status") != null, SysUser::getStatus, query.get("status"));
        wrapper.eq(query.get("roleId") != null, SysUser::getRoleId, query.get("roleId"));
        wrapper.orderByDesc(SysUser::getId);
        return page(new Page<>(pageNum, pageSize), wrapper);
    }


    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        sysSmsCodeService.verity(userRegisterDTO);
        SysUser user = new SysUser();
        user.setMobile(userRegisterDTO.getMobile());
        user.setUsername(userRegisterDTO.getUsername());
        check(user);
        handlePassword(user, userRegisterDTO.getPassword());
        // 用户注册需将权限设置为普通用户
        user.setRoleId(2);
        //账号默认为启用
        user.setStatus(1);
        //注册时间
        user.setCreateTime(LocalDateTime.now());
        saveOrUpdate(user);
    }

    @Override
    public void editPassword(EditPasswordDTO dto) {
        SysUserDTO user = UserThreadLocal.getCurrentUser();
        SysUser sysUser = getById(user.getId());
        if (sysUser != null) {
            LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysUser::getPassword, SecureUtil.md5(dto.getPassword() + sysUser.getSalt()));
            SysUser currentUser = getOne(queryWrapper);
            if (currentUser != null) {
                //重新生成盐值
                handlePassword(currentUser, dto.getNewPassword());
                saveOrUpdate(currentUser);
            } else {
                throw new CustomException("原密码错误");
            }
        } else {
            throw new CustomException("用户未查询到");
        }
    }


    @Override
    public void resetPassword(resetPasswordBySmsCodeDTO dto) {
        sysSmsCodeService.verity(dto);
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getMobile, dto.getMobile());
        SysUser sysUser = getOne(wrapper);
        if (sysUser == null) {
            throw new CustomException("请检查手机号是否正确");
        }
        handlePassword(sysUser, dto.getPassword());
        super.saveOrUpdate(sysUser);
    }

    @Override
    public SysUserLoginVO login(UserLoginDTO userLoginDTO) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, userLoginDTO.getUsername()).or().eq(SysUser::getMobile, userLoginDTO.getUsername());
        SysUser userInfo = getOne(wrapper);
        if (userInfo == null) {
            throw new CustomException("用户不存在");
        }
        String salt = userInfo.getSalt();
        if (!SecureUtil.md5(userLoginDTO.getPassword() + salt).equals(userInfo.getPassword())) {
//            String inputEncryptedPassword = SecureUtil.md5(userLoginDTO.getPassword() + salt);
//            System.out.println("登录时明文密码: " + userLoginDTO.getPassword());
//            System.out.println("登录时盐值: " + salt);
//            System.out.println("登录时加密后的密码: " + inputEncryptedPassword);
//            System.out.println("数据库中存储的加密密码: " + userInfo.getPassword());
            throw new CustomException("密码错误");
        }
        return login(userInfo);
    }

    @Override
    public SysUserLoginVO smsLogin(UserLoginDTO userLoginDTO) {
        sysSmsCodeService.verity(userLoginDTO);
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getMobile, userLoginDTO.getMobile());
        SysUser sysUser = getOne(wrapper);
        if (sysUser == null) {
            throw new CustomException("请检查手机号是否正确");
        }
        return login(sysUser);
    }

    private SysUserLoginVO login(SysUser userInfo) {
        //检查用户状态是否是启用
        if (!(StateType.ENABLE.getKey().equals(userInfo.getStatus()))) {
            throw new CustomException("当前用户已经被禁用");
        }
        //生成jwt
        String token = JwtUtils.generateToken(userInfo);
        userInfo.selectRole(sysRoleService);
        SysUserLoginVO sysUserLoginVO = new SysUserLoginVO();
        BeanUtil.copyProperties(userInfo, sysUserLoginVO);
        sysUserLoginVO.setToken(token);
        sysUserLoginVO.setMenuTree(getMenu(userInfo.getRoleId()));
        return sysUserLoginVO;
    }

    private List<SysMenu> getMenu(Integer roleId) {
        List<SysRoleMenu> list = sysRoleMenuService.list(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        List<Integer> menuIds = list.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        return sysMenuService.tree(sysMenuService.list(new LambdaQueryWrapper<SysMenu>().in(SysMenu::getId, menuIds).eq(SysMenu::getStatus, 1)), 1);
    }


    @Override
    public String resetPassword(Integer id) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getId, id);
        if (count(wrapper) > 0) {
            SysUser sysUser = getOne(wrapper);
            handlePassword(sysUser, resetPassword);
            updateById(sysUser);
            return "重置成功，重置密码为：" + resetPassword;
        } else {
            throw new CustomException("未找到该用户");
        }
    }

    private void handlePassword(SysUser sysUser, String newPassword) {
        String salt = Utils.salt();
        sysUser.setSalt(salt);
        //密码加密
//        String encryptedPassword = SecureUtil.md5(newPassword + salt);
//        System.out.println("注册时明文密码: " + newPassword);
//        System.out.println("注册时盐值: " + salt);
//        System.out.println("注册时加密后的密码: " + encryptedPassword);
        sysUser.setPassword(SecureUtil.md5(newPassword + salt));
    }


    public List<Integer> search(String keyword) {
        List<Integer> result = new ArrayList<>();
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(SysUser::getUsername, keyword);
        List<SysUser> list = list(wrapper);
        if (list.size() == 0) {
            result.add(0);
        } else {
            result=list.stream().map(SysUser::getId).collect(Collectors.toList());
        }
        return result;
    }


}
