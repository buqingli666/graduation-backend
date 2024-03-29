package com.oit.mapper;

import com.github.pagehelper.Page;
import com.oit.annotation.AutoFill;
import com.oit.dto.SetmealPageQueryDTO;
import com.oit.entity.Setmeal;
import com.oit.enumeration.OperationType;
import com.oit.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealMapper {

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:59
     * @Description 根据分类id查询套餐的数量
     **/

    Integer countByCategoryId(Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/29 8:53
     * @Description 根据id修改套餐
     **/

    @AutoFill(OperationType.UPDATE)
    void update(Setmeal setmeal);

    /*
     * @Author buqingli
     * @Date 2024/3/29 10:14
     * @Description 新增套餐
     **/

    @AutoFill(value = OperationType.INSERT)
    void insert(Setmeal setmeal);

    /*
     * @Author buqingli
     * @Date 2024/3/29 10:39
     * @Description 分页查询
     **/

    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:13
     * @Description 根据id查询套餐
     **/

    Setmeal getById(Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/29 11:14
     * @Description 根据id删除套餐
     **/

    void deleteById(Long setmealId);
}
