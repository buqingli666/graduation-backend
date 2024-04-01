package com.oit.controller.admin;

import com.oit.dto.SetmealDTO;
import com.oit.dto.SetmealPageQueryDTO;
import com.oit.result.PageResult;
import com.oit.result.Result;
import com.oit.service.SetmealService;
import com.oit.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/03/29/9:40
 * @Description: 套餐管理控制层
 */

@RestController
@RequestMapping(value = "/admin/setmeal", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /*
     * @Author buqingli
     * @Date 2024/3/29 10:02
     * @Description 新增套餐
     **/

    @PostMapping
    @ApiOperation("新增套餐")
    @CacheEvict(cacheNames = "setmealCache", key = "#setmealDTO.categoryId")  //key: setmealCache::100
    public Result<?> save(@RequestBody SetmealDTO setmealDTO) {
        setmealService.saveWithDish(setmealDTO);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 10:32
     * @Description 分页查询
     **/

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success(pageResult);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:02
     * @Description 批量删除套餐
     **/

    @DeleteMapping
    @ApiOperation("批量删除套餐")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result<?> delete(@RequestParam List<Long> ids) {
        setmealService.deleteBatch(ids);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:17
     * @Description 根据id查询套餐，用于修改页面回显数据
     **/

    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealVO> getById(@PathVariable Long id) {
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return Result.success(setmealVO);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:19
     * @Description 修改套餐
     **/

    @PutMapping
    @ApiOperation("修改套餐")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result<?> update(@RequestBody SetmealDTO setmealDTO) {
        setmealService.update(setmealDTO);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 12:00
     * @Description 套餐起售停售
     **/

    @PostMapping("/status/{status}")
    @ApiOperation("套餐起售停售")
    @CacheEvict(cacheNames = "setmealCache", allEntries = true)
    public Result<?> startOrStop(@PathVariable Integer status, Long id) {
        setmealService.startOrStop(status, id);
        return Result.success();
    }

}
