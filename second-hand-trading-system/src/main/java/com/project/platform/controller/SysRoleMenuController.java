package com.project.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.common.PageVO;
import com.project.platform.common.Result;
import com.project.platform.entity.SysRoleMenu;
import com.project.platform.service.SysRoleMenuService;
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
 * 角色菜单 前端控制器
 * </p>
 *
 *
 *
 */
@RestController
@RequestMapping("/sysRoleMenu")
public class SysRoleMenuController {
    @Resource
    private SysRoleMenuService service;

    /**
     * 列表
     */
    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Result<List<SysRoleMenu>> list() {
        return new Result<>().success(service.list());
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<PageVO<SysRoleMenu>> findPage(@RequestParam Map<String, Object> query, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<SysRoleMenu> page = service.page(query, pageNum, pageSize);
        return new Result<>().success(new PageVO<>(page));
    }

    /**
     * 数据新增
     */
    @ApiOperation(value = "数据新增", notes = "数据新增")
    @PostMapping("/add/{roleId}")
    public Result add(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        service.save(roleId, menuIds);
        return new Result<>().success();
    }

    /**
     * 数据更新
     */
    @ApiOperation(value = "数据更新", notes = "数据更新")
    @PutMapping()
    public Result update(@Validated @RequestBody SysRoleMenu entity) {
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
    public Result<SysRoleMenu> getById(@PathVariable("id") Integer id) {
        SysRoleMenu entity = service.getById(id);
        return new Result<>().success(entity);
    }

    @ApiOperation(value = "根据角色id查询", notes = "根据角色id查询")
    @GetMapping("/getByRoleId/{roleId}")
    public Result<List<SysRoleMenu>> getByRoleId(@PathVariable Integer roleId) {
        return new Result<>().success(service.list(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId)));
    }
}
