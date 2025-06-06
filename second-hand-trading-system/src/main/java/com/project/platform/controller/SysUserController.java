package com.project.platform.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.common.PageVO;
import com.project.platform.common.Result;
import com.project.platform.dto.EditPasswordDTO;
import com.project.platform.dto.UserLoginDTO;
import com.project.platform.dto.UserRegisterDTO;
import com.project.platform.dto.resetPasswordBySmsCodeDTO;
import com.project.platform.entity.SysUser;
import com.project.platform.service.SysRoleService;
import com.project.platform.service.SysUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 *
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {
    @Resource
    private SysUserService service;

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Result<List<SysUser>> list() {
        return new Result<>().success(service.list());
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<PageVO<SysUser>> findPage(@RequestParam Map<String, Object> query, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysUser> page = service.page(query, pageNum, pageSize);
        page.getRecords().forEach(item -> {
            item.selectRole(sysRoleService);
        });
        return new Result<>().success(new PageVO<>(page));
    }

    /**
     * 数据新增
     */
    @ApiOperation(value = "数据新增", notes = "数据新增")
    @PostMapping()
    public Result add(@Validated @RequestBody SysUser entity) {
        service.save(entity);
        return new Result<>().success();
    }

    /**
     * 数据更新
     */
    @ApiOperation(value = "数据更新", notes = "数据更新")
    @PutMapping()
    public Result update(@Validated @RequestBody SysUser entity) {
        service.updateById(entity);
        return new Result<>().success();
    }

    /**
     * 根据id删除
     */
    @ApiOperation(value = "数据根据id批量删除", notes = "数据根据id批量删除")
    @DeleteMapping()
    public Result delBatch(@RequestBody List<Integer> ids) {
        service.removeByIds(ids);
        return new Result<>().success();
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping("/{id}")
    public Result<SysUser> getById(@PathVariable("id") Integer id) {
        SysUser entity = service.getById(id);
        return new Result<>().success(entity);
    }


    /**
     * 用户登录
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        return new Result<>().success(service.login(userLoginDTO));
    }

    @PostMapping("/smsLogin")
    public Result smsLogin(@Validated @RequestBody UserLoginDTO userLoginDTO) {
        return new Result<>().success(service.smsLogin(userLoginDTO));
    }

    /**
     * 用户注册
     *
     * @param userRegisterDTO
     * @return
     */
    @PostMapping("/register")
    public Result register(@Validated @RequestBody UserRegisterDTO userRegisterDTO) {
        service.register(userRegisterDTO);
        return new Result<>().success();
    }

    /**
     * 修改密码
     *
     * @param dto
     * @return
     */
    @PutMapping("/editPassword")
    public Result editPassword(@RequestBody EditPasswordDTO dto) {
        service.editPassword(dto);
        return new Result<>().success();
    }


    @PutMapping("/resetPasswordBySmsCode")
    public Result editPasswordBySmsCode(@RequestBody resetPasswordBySmsCodeDTO dto) {
        service.resetPassword(dto);
        return new Result<>().success();
    }

    /**
     * 数据更新
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "密码重置", notes = "密码重置")
    @PutMapping("/resetPassword/{id}")
    public Result resetPassword(@PathVariable Integer id) {
        return new Result<>().success(service.resetPassword(id));
    }
}
