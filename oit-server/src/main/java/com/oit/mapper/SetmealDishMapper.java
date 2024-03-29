package com.oit.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /*
     * @Author buqingli
     * @Date 2024/3/28 15:32
     * @Description 根据菜品id查询对应的套餐id
     **/

    // select setmeal_id from setmeal_dish where dish_id in (1,2,3,4)
    List<Long> getSetmealIdsByDishIds(List<Long> ids);

}
