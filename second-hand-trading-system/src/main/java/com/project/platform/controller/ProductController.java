package com.project.platform.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.common.PageVO;
import com.project.platform.common.Result;
import com.project.platform.entity.Product;
import com.project.platform.service.ProductFavoriteService;
import com.project.platform.service.ProductService;
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
 * 商品 前端控制器
 * </p>
 *
 *
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService service;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private ProductFavoriteService productFavoriteService;

    /**
     * 列表
     */
    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Result<List<Product>> list() {
        return new Result<>().success(service.list());
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<PageVO<Product>> findPage(@RequestParam Map<String, Object> query, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<Product> page = service.page(query, pageNum, pageSize);
        page.getRecords().forEach(item -> {
            item.selectUserName(sysUserService);
            item.selectProductFavorite(productFavoriteService);
        });
        return new Result<>().success(new PageVO<>(page));
    }

    /**
     * 数据新增
     */
    @ApiOperation(value = "数据新增", notes = "数据新增")
    @PostMapping()
    public Result add(@Validated @RequestBody Product entity) {
        service.save(entity);
        return new Result<>().success();
    }

    /**
     * 数据更新
     */
    @ApiOperation(value = "数据更新", notes = "数据更新")
    @PutMapping()
    public Result update(@Validated @RequestBody Product entity) {
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
    public Result<Product> getById(@PathVariable("id") Integer id) {
        Product entity = service.getById(id);
        entity.selectUserInfo(sysUserService);
        entity.selectProductFavorite(productFavoriteService);
        return new Result<>().success(entity);
    }


    @GetMapping("/top/{size}")
    public Result<List<Product>> findTop(@PathVariable Integer size) {
        // 创建LambdaQueryWrapper实例
        LambdaQueryWrapper<Product> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(Product::getStatus, 1);
        // 排序，根据播放数降序排列
        lambdaQueryWrapper.orderByDesc(Product::getPageView);
        // 限制结果数量为9条
        lambdaQueryWrapper.last("LIMIT " + size);
        List<Product> list = service.list(lambdaQueryWrapper);
        list.forEach(item -> {
            item.selectUserName(sysUserService);
        });
        return new Result<>().success(list);
    }

    @GetMapping("/recommend/{size}/{pageNumber}")
    public Result recommend(@PathVariable Integer size,@PathVariable Integer pageNumber) {
        List<Product> list = service.recommend(size,pageNumber);
        return new Result<>().success(list);
    }


}
