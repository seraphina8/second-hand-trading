package com.project.platform.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.platform.common.PageVO;
import com.project.platform.common.Result;
import com.project.platform.entity.ProductClassify;
import com.project.platform.service.ProductClassifyService;
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
 * 商品分类 前端控制器
 * </p>
 *
 *
 *
 */
@RestController
@RequestMapping("/productClassify")
public class ProductClassifyController {
    @Resource
    private ProductClassifyService service;
    /**
     * 列表
     */
    @ApiOperation(value = "列表", notes = "列表")
    @GetMapping("/list")
    public Result<List<ProductClassify>> list() {
        return new Result<>().success(service.list());
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page")
    public Result<PageVO<ProductClassify>> findPage(@RequestParam Map<String, Object> query,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize) {
        Page<ProductClassify> page = service.page(query, pageNum, pageSize);
        return new Result<>().success(new PageVO<>(page));
    }

    /**
     * 数据新增
     */
    @ApiOperation(value = "数据新增", notes = "数据新增")
    @PostMapping()
    public Result add(@Validated @RequestBody ProductClassify entity) {
        service.save(entity);
        return new Result<>().success();
    }

    /**
     * 数据更新
     */
    @ApiOperation(value = "数据更新", notes = "数据更新")
    @PutMapping()
    public Result update(@Validated @RequestBody ProductClassify entity) {
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
    public Result<ProductClassify> getById(@PathVariable("id") Integer id) {
        ProductClassify entity = service.getById(id);
        return new Result<>().success(entity);
    }
}
