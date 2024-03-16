package com.oit.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper {

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:58
     * @Description 根据分类id查询菜品数量
     **/

    Integer countByCategoryId(Long categoryId);

}
