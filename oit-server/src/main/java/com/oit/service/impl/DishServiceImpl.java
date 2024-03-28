package com.oit.service.impl;

import com.oit.dto.DishDTO;
import com.oit.entity.Dish;
import com.oit.entity.DishFlavor;
import com.oit.mapper.DishFlavorMapper;
import com.oit.mapper.DishMapper;
import com.oit.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/03/26/17:28
 * @Description: 菜品管理业务层
 */

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    /*
     * @Author buqingli
     * @Date 2024/3/28 8:54
     * @Description 新增菜品和对应的口味
     **/

    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        //向菜品表插入1条数据
        dishMapper.insert(dish);
        //获取insert语句生成的主键值
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (ObjectUtils.isNotEmpty(flavors)) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }
}