package com.project.platform.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.project.platform.common.PageVO;
import com.project.platform.common.Result;
import com.project.platform.dto.SysUserDTO;
import com.project.platform.entity.ProductLeaveMessage;
import com.project.platform.service.ProductLeaveMessageService;
import com.project.platform.service.ProductService;
import com.project.platform.service.SysUserService;
import com.project.platform.utils.UserThreadLocal;
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
 * 商品留言 前端控制器
 * </p>
 *
 * 
 */
@RestController
@RequestMapping("/productLeaveMessage")
public class ProductLeaveMessageController {
    @Resource
    private ProductLeaveMessageService service;

    @Resource
    private SysUserService sysUserService;
    @Resource
    private ProductService productService;



    /**
     * 列表
     */
    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Result<List<ProductLeaveMessage>> list() {
        return new Result<>().success(service.list());
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<PageVO<ProductLeaveMessage>> findPage(@RequestParam Map<String, Object> query, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        SysUserDTO currentUser = UserThreadLocal.getCurrentUser();
        if (!currentUser.isAdmin()){
            query.put("userId",currentUser.getId());
        }
        Page<ProductLeaveMessage> page = service.page(query, pageNum, pageSize);
        page.getRecords().forEach(item -> {
            item.selectUserInfo(sysUserService);
            item.selectSellerName(productService);
        });
        return new Result<>().success(new PageVO<>(page));
    }

    /**
     * 数据新增
     */
    @ApiOperation(value = "数据新增", notes = "数据新增")
    @PostMapping("/add")
    public Result add(@Validated @RequestBody ProductLeaveMessage entity) {
        service.save(entity);
        return new Result<>().success();
    }

    /**
     * 数据更新
     */
    @ApiOperation(value = "数据更新", notes = "数据更新")
    @PutMapping("/update")
    public Result update(@Validated @RequestBody ProductLeaveMessage entity) {
        service.updateById(entity);
        return new Result<>().success();
    }

    /**
     * 根据id删除
     */
    @ApiOperation(value = "数据根据id批量删除", notes = "数据根据id批量删除")
    @DeleteMapping("/delete")
    public Result delBatch(@RequestBody List<Integer> ids) {
        service.removeByIds(ids);
        return new Result<>().success();
    }

    /**
     * 根据id查询
     */
    @ApiOperation(value = "根据id查询", notes = "根据id查询")
    @GetMapping("/{id}")
    public Result<ProductLeaveMessage> getById(@PathVariable("id") Integer id) {
        ProductLeaveMessage entity = service.getById(id);
        return new Result<>().success(entity);
    }
}
