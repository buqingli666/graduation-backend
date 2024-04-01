package com.oit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oit.constant.MessageConstant;
import com.oit.constant.StatusConstant;
import com.oit.dto.SetmealDTO;
import com.oit.dto.SetmealPageQueryDTO;
import com.oit.entity.Dish;
import com.oit.entity.Setmeal;
import com.oit.entity.SetmealDish;
import com.oit.exception.DeletionNotAllowedException;
import com.oit.exception.SetmealEnableFailedException;
import com.oit.mapper.DishMapper;
import com.oit.mapper.SetmealDishMapper;
import com.oit.mapper.SetmealMapper;
import com.oit.result.PageResult;
import com.oit.service.SetmealService;
import com.oit.vo.DishItemVO;
import com.oit.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/03/29/9:43
 * @Description: 套餐管理业务层
 */

@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {


    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private DishMapper dishMapper;

    /*
     * @Author buqingli
     * @Date 2024/3/29 10:11
     * @Description 新增套餐，同时需要保存套餐和菜品的关联关系
     **/

    @Override
    @Transactional
    public void saveWithDish(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        //向套餐表插入数据
        setmealMapper.insert(setmeal);

        //获取生成的套餐id
        Long setmealId = setmeal.getId();

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });

        //保存套餐和菜品的关联关系
        setmealDishMapper.insertBatch(setmealDishes);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 10:38
     * @Description 分页查询
     **/

    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:03
     * @Description 批量删除套餐
     **/

    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        //1.判断当前套餐是否能够删除---是否存在起售中的套餐？？？
        ids.forEach(id -> {
            Setmeal setmeal = setmealMapper.getById(id);
            if (setmeal.getStatus().equals(StatusConstant.ENABLE)) {
                //抛出起售中的套餐不能删除的业务异常
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });
        ids.forEach(setmealId -> {
            //删除套餐表中的数据
            setmealMapper.deleteById(setmealId);
            //删除套餐菜品关系表中的数据
            setmealDishMapper.deleteBySetmealId(setmealId);
        });
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:20
     * @Description 根据id查询套餐和套餐菜品关系
     **/

    @Override
    public SetmealVO getByIdWithDish(Long id) {
        //根据套餐id查询套餐
        Setmeal setmeal = setmealMapper.getById(id);
        //根据套餐id查询套餐和菜品的关联关系
        List<SetmealDish> setmealDishes = setmealDishMapper.getBySetmealId(id);

        SetmealVO setmealVO = new SetmealVO();
        BeanUtils.copyProperties(setmeal, setmealVO);
        setmealVO.setSetmealDishes(setmealDishes);

        return setmealVO;
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:21
     * @Description 修改套餐
     **/

    @Override
    @Transactional
    public void update(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        //1.修改套餐表，执行update
        setmealMapper.update(setmeal);
        //获取套餐id
        Long setmealId = setmealDTO.getId();
        //2.删除套餐和菜品的关联关系，操作setmeal_dish表，执行delete
        setmealDishMapper.deleteBySetmealId(setmealId);
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(setmealDish -> {
            setmealDish.setSetmealId(setmealId);
        });
        //3.重新插入套餐和菜品的关联关系，操作setmeal_dish表，执行insert
        setmealDishMapper.insertBatch(setmealDishes);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/29 12:03
     * @Description 套餐起售、停售
     **/

    @Override
    @Transactional
    public void startOrStop(Integer status, Long id) {
        //起售套餐时，判断套餐内是否有停售菜品，有停售菜品提示"套餐内包含未启售菜品，无法启售"
        if (status.equals(StatusConstant.ENABLE)) {
            //select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = ?
            List<Dish> dishList = dishMapper.getBySetmealId(id);
            if (ObjectUtils.isNotEmpty(dishList)) {
                dishList.forEach(dish -> {
                    if (dish.getStatus().equals(StatusConstant.DISABLE)) {
                        throw new SetmealEnableFailedException(MessageConstant.SETMEAL_ENABLE_FAILED);
                    }
                });
            }
        }
        Setmeal setmeal = Setmeal.builder()
                .id(id)
                .status(status)
                .build();
        setmealMapper.update(setmeal);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/1 14:45
     * @Description 条件查询
     **/

    @Override
    public List<Setmeal> list(Setmeal setmeal) {
        return setmealMapper.list(setmeal);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/1 15:01
     * @Description 根据套餐id查询包含的菜品项列表
     **/

    @Override
    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }
}
