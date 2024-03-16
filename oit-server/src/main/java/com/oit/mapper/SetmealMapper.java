package com.oit.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealMapper {

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:59
     * @Description 根据分类id查询套餐的数量
     **/

    Integer countByCategoryId(Long id);

}
