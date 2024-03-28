package com.oit.mapper;

import com.oit.annotation.AutoFill;
import com.oit.entity.Dish;
import com.oit.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:58
     * @Description 根据分类id查询菜品数量
     **/

    Integer countByCategoryId(Long categoryId);

    /*
     * @Author buqingli
     * @Date 2024/3/28 9:07
     * @Description 插入菜品数据
     **/

    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

}
