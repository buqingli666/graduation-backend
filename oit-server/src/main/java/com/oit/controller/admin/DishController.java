package com.oit.controller.admin;

import com.oit.dto.DishDTO;
import com.oit.result.Result;
import com.oit.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品：{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }
}
