package com.oit.mapper;

import com.github.pagehelper.Page;
import com.oit.annotation.AutoFill;
import com.oit.dto.DishPageQueryDTO;
import com.oit.entity.Dish;
import com.oit.enumeration.OperationType;
import com.oit.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:58
     * @Description 根据分类id查询菜品数量
     **/

    Integer countByCategoryId(Long categoryId);

    /*
     * @Author buqingli
     * @Date 2024/3/28 9:07
     * @Description 插入菜品数据
     **/

    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /*
     * @Author buqingli
     * @Date 2024/3/28 11:07
     * @Description 菜品分页查询
     **/

    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/28 15:31
     * @Description 根据菜品id查询菜品
     **/

    Dish getById(Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/28 15:36
     * @Description 根据菜品id删除菜品数据
     **/

    void deleteById(Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/28 15:45
     * @Description 根据菜品id集合批量删除菜品数据
     **/

    void deleteByIds(List<Long> ids);
}
