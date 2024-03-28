package com.oit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oit.constant.MessageConstant;
import com.oit.constant.StatusConstant;
import com.oit.dto.DishDTO;
import com.oit.dto.DishPageQueryDTO;
import com.oit.entity.Dish;
import com.oit.entity.DishFlavor;
import com.oit.entity.SetmealDish;
import com.oit.exception.DeletionNotAllowedException;
import com.oit.exception.DishStopFailedException;
import com.oit.mapper.DishFlavorMapper;
import com.oit.mapper.DishMapper;
import com.oit.mapper.SetmealDishMapper;
import com.oit.result.PageResult;
import com.oit.service.DishService;
import com.oit.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/03/26/17:28
 * @Description: 菜品管理业务层
 */

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /*
     * @Author buqingli
     * @Date 2024/3/28 8:54
     * @Description 新增菜品和对应的口味
     **/

    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        //向菜品表插入1条数据
        dishMapper.insert(dish);
        //获取insert语句生成的主键值
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (ObjectUtils.isNotEmpty(flavors)) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /*
     * @Author buqingli
     * @Date 2024/3/28 11:04
     * @Description 菜品分页查询
     **/

    @Override
    public PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /*
     * @Author buqingli
     * @Date 2024/3/28 11:25
     * @Description 菜品批量删除
     **/

    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        //1.判断当前菜品是否能够删除---是否存在启售中的菜品？？？
        for (Long id : ids) {
            Dish dish = dishMapper.getById(id);
            if (dish.getStatus().equals(StatusConstant.ENABLE)) {
                //抛出当前菜品处于起售中，不能删除异常
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }

        //2.判断当前菜品是否能够删除---是否被套餐关联了？？？
        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if (ObjectUtils.isNotEmpty(setmealIds)) {
            //抛出当前菜品被套餐关联了，不能删除异常
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }

        //3.删除菜品表中的菜品数据
        //for (Long id : ids) {
        //dishMapper.deleteById(id);
        //删除菜品关联的口味数据
        //dishFlavorMapper.deleteByDishId(id);
        //}

        //3.1根据菜品id集合批量删除菜品数据
        //sql: delete from dish where id in (?,?,?)
        dishMapper.deleteByIds(ids);

        //3.2根据菜品id集合批量删除关联的口味数据
        //sql: delete from dish_flavor where dish_id in (?,?,?)
        dishFlavorMapper.deleteByDishIds(ids);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/28 16:22
     * @Description 根据id查询菜品和对应的口味数据
     **/

    @Override
    public DishVO getByIdWithFlavor(Long id) {
        //1.根据id查询菜品数据
        Dish dish = dishMapper.getById(id);
        //2.根据菜品id查询口味数据
        List<DishFlavor> dishFlavors = dishFlavorMapper.getByDishId(id);
        //3.将查询到的数据封装到VO
        DishVO dishVO = new DishVO();
        BeanUtils.copyProperties(dish, dishVO);
        dishVO.setFlavors(dishFlavors);
        return dishVO;
    }

    /*
     * @Author buqingli
     * @Date 2024/3/28 16:28
     * @Description 根据id修改菜品基本信息和对应的口味信息
     **/

    @Override
    @Transactional
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        //1.修改菜品表基本信息
        dishMapper.update(dish);
        //2.删除原有的口味数据
        dishFlavorMapper.deleteByDishId(dishDTO.getId());
        //3.重新插入口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (ObjectUtils.isNotEmpty(flavors)) {
            flavors.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishDTO.getId());
            });
            //向口味表插入n条数据
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /*
     * @Author buqingli
     * @Date 2024/3/28 17:16
     * @Description 启售/停售菜品
     **/

    @Override
    public void startOrStop(Integer status, Long id) {
        //被套餐关联的菜品不能被停售---通过菜品id查setmeal_dish表
        SetmealDish setmealDish = setmealDishMapper.getSetmealIdsByDishId(id);
        if (ObjectUtils.isNotEmpty(setmealDish)) {
            //抛出当前菜品被套餐关联了，不能停售异常
            throw new DishStopFailedException(MessageConstant.DISH_BE_STOPED_BY_SETMEAL);
        }
        Dish dish = Dish.builder()
                .id(id)
                .status(status)
                .build();
        dishMapper.update(dish);
    }

}