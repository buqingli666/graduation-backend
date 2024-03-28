package com.oit.service;

import com.oit.dto.DishDTO;

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
}
