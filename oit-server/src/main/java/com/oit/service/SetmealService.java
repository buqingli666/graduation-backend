package com.oit.service;

import com.oit.dto.SetmealDTO;
import com.oit.dto.SetmealPageQueryDTO;
import com.oit.entity.Setmeal;
import com.oit.result.PageResult;
import com.oit.vo.DishItemVO;
import com.oit.vo.SetmealVO;

import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/03/29/9:42
 * @Description: 套餐管理服务层
 */

public interface SetmealService {

    /*
     * @Author buqingli
     * @Date 2024/3/29 10:03
     * @Description 新增套餐，同时需要保存套餐和菜品的关联关系
     **/

    void saveWithDish(SetmealDTO setmealDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/29 10:32
     * @Description 分页查询
     **/

    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:02
     * @Description 批量删除套餐
     **/

    void deleteBatch(List<Long> ids);

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:19
     * @Description 根据id查询套餐和关联的菜品数据
     **/

    SetmealVO getByIdWithDish(Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:20
     * @Description 修改套餐
     **/

    void update(SetmealDTO setmealDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/29 12:01
     * @Description 套餐起售、停售
     **/

    void startOrStop(Integer status, Long id);

    /*
     * @Author buqingli
     * @Date 2024/4/1 14:42
     * @Description 条件查询
     **/

    List<Setmeal> list(Setmeal setmeal);

    /*
     * @Author buqingli
     * @Date 2024/4/1 15:00
     * @Description 根据套餐id查询包含的菜品项列表
     **/

    List<DishItemVO> getDishItemById(Long id);
}
