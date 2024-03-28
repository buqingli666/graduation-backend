package com.oit.service;

import com.oit.dto.DishDTO;
import com.oit.dto.DishPageQueryDTO;
import com.oit.result.PageResult;

import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/03/26/17:27
 * @Description: 菜品管理服务层
 */

public interface DishService {

    /*
     * @Author buqingli
     * @Date 2024/3/28 8:52
     * @Description 新增菜品和对应的口味
     **/

    void saveWithFlavor(DishDTO dishDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/28 11:03
     * @Description 菜品分页查询
     **/

    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/28 11:24
     * @Description 菜品批量删除
     **/

    void deleteBatch(List<Long> ids);

}
