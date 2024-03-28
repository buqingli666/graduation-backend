package com.oit.service;

import com.oit.dto.DishDTO;
import com.oit.dto.DishPageQueryDTO;
import com.oit.result.PageResult;
import com.oit.vo.DishVO;

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

    /*
     * @Author buqingli
     * @Date 2024/3/28 16:21
     * @Description 根据id查询菜品和对应的口味数据
     **/

    DishVO getByIdWithFlavor(Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/28 16:27
     * @Description 根据id修改菜品基本信息和对应的口味信息
     **/

    void updateWithFlavor(DishDTO dishDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/28 17:05
     * @Description 起售/停售菜品
     **/

    void startOrStop(Integer status, Long id);
}
