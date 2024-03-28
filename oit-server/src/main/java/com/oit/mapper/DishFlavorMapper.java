package com.oit.mapper;

import com.oit.entity.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /*
     * @Author buqingli
     * @Date 2024/3/28 9:11
     * @Description 批量插入口味数据
     **/

    void insertBatch(List<DishFlavor> flavors);

    /*
     * @Author buqingli
     * @Date 2024/3/28 15:37
     * @Description 根据菜品id删除对应的口味数据
     **/

    void deleteByDishId(Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/28 15:53
     * @Description 根据菜品id集合批量删除关联的口味数据
     **/

    void deleteByDishIds(List<Long> ids);

    /*
     * @Author buqingli
     * @Date 2024/3/28 16:23
     * @Description 根据菜品id查询口味数据
     **/

    List<DishFlavor> getByDishId(Long dishId);
}
