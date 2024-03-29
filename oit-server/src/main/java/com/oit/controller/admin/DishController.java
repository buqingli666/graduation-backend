package com.oit.controller.admin;

import com.oit.dto.DishDTO;
import com.oit.dto.DishPageQueryDTO;
import com.oit.entity.Dish;
import com.oit.result.PageResult;
import com.oit.result.Result;
import com.oit.service.DishService;
import com.oit.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/03/26/17:23
 * @Description: 菜品管理控制层
 */
@RestController
@RequestMapping(value = "/admin/dish", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "菜品相关接口")
public class DishController {

    @Autowired
    private DishService dishService;

    /*
     * @Author buqingli
     * @Date 2024/3/28 8:50
     * @Description 新增菜品
     **/

    @PostMapping
    @ApiOperation("新增菜品")
    public Result<?> save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/28 11:03
     * @Description 菜品分页查询
     **/

    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("菜品分页查询:{}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/28 11:24
     * @Description 菜品批量删除
     **/

    @DeleteMapping
    @ApiOperation("菜品批量删除")
    public Result<?> delete(@RequestParam List<Long> ids) {
        log.info("菜品批量删除：{}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/28 16:20
     * @Description 根据id查询菜品
     **/

    @GetMapping("/{id}")
    @ApiOperation("根据id查询菜品")
    public Result<DishVO> getById(@PathVariable Long id) {
        log.info("根据id查询菜品：{}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);//后绪步骤实现
        return Result.success(dishVO);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/28 16:27
     * @Description 修改菜品
     **/

    @PutMapping
    @ApiOperation("修改菜品")
    public Result<?> update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品：{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/28 16:59
     * @Description 起售/停售菜品
     **/

    @PostMapping("/status/{status}")
    @ApiOperation("起售/停售菜品")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {
        log.info("菜品起售/停售：{},{}", status == 1 ? "起售" : "停售", id);
        dishService.startOrStop(status, id);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 9:30
     * @Description 根据分类id查询菜品
     **/

    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<Dish>> list(Long categoryId) {
        List<Dish> list = dishService.list(categoryId);
        return Result.success(list);
    }

}
