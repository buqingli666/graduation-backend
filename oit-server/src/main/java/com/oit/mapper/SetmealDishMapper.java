package com.oit.mapper;

import com.oit.entity.SetmealDish;
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

    /*
     * @Author buqingli
     * @Date 2024/3/29 10:23
     * @Description 批量保存套餐和菜品的关联关系
     **/

    void insertBatch(List<SetmealDish> setmealDishes);

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:15
     * @Description 根据套餐id删除套餐和菜品的关联关系
     **/

    void deleteBySetmealId(Long setmealId);

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:24
     * @Description 根据套餐id查询套餐和菜品的关联关系
     **/

    List<SetmealDish> getBySetmealId(Long setmealId);

}
