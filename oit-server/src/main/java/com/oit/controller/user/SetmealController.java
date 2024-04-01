package com.oit.controller.user;

import com.oit.constant.StatusConstant;
import com.oit.entity.Setmeal;
import com.oit.result.Result;
import com.oit.service.SetmealService;
import com.oit.vo.DishItemVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * @Author buqingli
 * @Date 2024/4/1 14:35
 * @Description C端-套餐浏览控制层
 **/

@RestController("userSetmealController")
@RequestMapping(value = "/user/setmeal", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "C端-套餐浏览接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /*
     * @Author buqingli
     * @Date 2024/4/1 14:37
     * @Description 条件查询
     **/

    @GetMapping("/list")
    @ApiOperation("根据分类id查询套餐")
    @Cacheable(cacheNames = "setmealCache", key = "#categoryId") //key: setmealCache::100
    public Result<List<Setmeal>> list(Long categoryId) {
        log.info("当前分类id为:{}", categoryId);
        Setmeal setmeal = new Setmeal();
        setmeal.setCategoryId(categoryId);
        setmeal.setStatus(StatusConstant.ENABLE);
        List<Setmeal> list = setmealService.list(setmeal);
        return Result.success(list);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/1 14:38
     * @Description 根据套餐id查询包含的菜品项列表
     **/

    @GetMapping("/dish/{id}")
    @ApiOperation("根据套餐id查询包含的菜品项列表")
    public Result<List<DishItemVO>> dishList(@PathVariable("id") Long id) {
        log.info("当前套餐id为:{}", id);
        List<DishItemVO> list = setmealService.getDishItemById(id);
        return Result.success(list);
    }
}
