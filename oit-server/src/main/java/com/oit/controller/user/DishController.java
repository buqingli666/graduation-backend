package com.oit.controller.user;

import com.oit.constant.StatusConstant;
import com.oit.entity.Dish;
import com.oit.result.Result;
import com.oit.service.DishService;
import com.oit.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate redisTemplate;

    /*
     * @Author buqingli
     * @Date 2024/4/1 14:06
     * @Description 根据分类id查询菜品
     **/

    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public Result<List<DishVO>> list(Long categoryId) {
        log.info("当前分类id为:{}", categoryId);
        //构造redis中的key，规则：dish_分类id
        String key = "dish_" + categoryId;
        //查询redis中是否存在菜品数据
        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if (ObjectUtils.isNotEmpty(list)) {
            //如果存在，直接返回，无须查询数据库
            return Result.success(list);
        }
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        //查询起售中的菜品
        dish.setStatus(StatusConstant.ENABLE);
        list = dishService.listWithFlavor(dish);
        redisTemplate.opsForValue().set(key, list);
        return Result.success(list);
    }

}
