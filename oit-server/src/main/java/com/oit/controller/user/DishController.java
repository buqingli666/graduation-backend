package com.oit.controller.user;

import com.oit.constant.StatusConstant;
import com.oit.entity.Dish;
import com.oit.result.Result;
import com.oit.service.DishService;
import com.oit.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * @Author buqingli
 * @Date 2024/4/1 14:06
 * @Description C端-菜品浏览控制层
 **/

@RestController("userDishController")
@RequestMapping(value = "/user/dish", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "C端-菜品浏览接口")
public class DishController {

    @Autowired
    private DishService dishService;

    /*
     * @Author buqingli
     * @Date 2024/4/1 14:06
     * @Description 根据分类id查询菜品
     **/

    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        log.info("当前分类id为:{}", categoryId);
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        //查询起售中的菜品
        dish.setStatus(StatusConstant.ENABLE);
        List<DishVO> list = dishService.listWithFlavor(dish);
        return Result.success(list);
    }

}
