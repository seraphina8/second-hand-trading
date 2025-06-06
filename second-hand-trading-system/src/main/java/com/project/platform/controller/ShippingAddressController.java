package com.project.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.common.PageVO;
import com.project.platform.common.Result;
import com.project.platform.entity.ShippingAddress;
import com.project.platform.service.ShippingAddressService;
import com.project.platform.service.SysUserService;
import com.project.platform.utils.Utils;
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
 * 收货地址 前端控制器
 * </p>
 *
 *
 *
 */
@RestController
@RequestMapping("/shippingAddress")
public class ShippingAddressController {
    @Resource
    private ShippingAddressService service;

    @Resource
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Result<List<ShippingAddress>> list() {
        LambdaQueryWrapper<ShippingAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(!Utils.getUser().isAdmin(), ShippingAddress::getUserId, Utils.getUser().getId());
        return new Result<>().success(service.list(wrapper));
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<PageVO<ShippingAddress>> findPage(@RequestParam Map<String, Object> query,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize) {
        Page<ShippingAddress> page = service.page(query, pageNum, pageSize);
        page.getRecords().forEach(item -> {
            item.selectUserName(sysUserService);
        });
        return new Result<>().success(new PageVO<>(page));
    }

    /**
     * 数据新增
     */
    @ApiOperation(value = "数据新增", notes = "数据新增")
    @PostMapping()
    public Result add(@Validated @RequestBody ShippingAddress entity) {
        service.save(entity);
        return new Result<>().success();
    }

    /**
     * 数据更新
     */
    @ApiOperation(value = "数据更新", notes = "数据更新")
    @PutMapping()
    public Result update(@Validated @RequestBody ShippingAddress entity) {
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
    public Result<ShippingAddress> getById(@PathVariable("id") Integer id) {
        ShippingAddress entity = service.getById(id);
        return new Result<>().success(entity);
    }
}
