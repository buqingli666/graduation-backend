package com.oit.controller.admin;

import com.oit.dto.CategoryDTO;
import com.oit.dto.CategoryPageQueryDTO;
import com.oit.entity.Category;
import com.oit.result.PageResult;
import com.oit.result.Result;
import com.oit.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @Author buqingli
 * @Date 2024/3/14 15:58
 * @Description 分类管理控制层
 **/

@RestController
@RequestMapping(value = "/admin/category", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "分类相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*
     * @Author buqingli
     * @Date 2024/3/14 15:59
     * @Description 新增分类
     **/

    @PostMapping
    @ApiOperation("新增分类")
    public Result<String> save(@RequestBody CategoryDTO categoryDTO) {
        log.info("新增分类:{}", categoryDTO);
        categoryService.save(categoryDTO);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:02
     * @Description 分类分页查询
     **/

    @GetMapping("/page")
    @ApiOperation("分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分页查询:{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:03
     * @Description 删除分类
     **/

    @DeleteMapping
    @ApiOperation("删除分类")
    public Result<String> deleteById(Long id) {
        log.info("删除分类:{}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:03
     * @Description 修改分类
     **/

    @PutMapping
    @ApiOperation("修改分类")
    public Result<String> update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:04
     * @Description 启用/禁用分类
     **/

    @PostMapping("/status/{status}")
    @ApiOperation("启用/禁用分类")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:04
     * @Description 根据类型查询分类
     **/

    @GetMapping("/list")
    @ApiOperation("根据类型查询分类")
    public Result<List<Category>> list(Integer type) {
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }

}
