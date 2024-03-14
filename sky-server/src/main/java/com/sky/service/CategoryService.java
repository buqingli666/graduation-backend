package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import java.util.List;

/*
 * @Author buqingli
 * @Date 2024/3/14 16:06
 * @Description 分类管理服务层
 **/

public interface CategoryService {

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:06
     * @Description 新增分类
     **/

    void save(CategoryDTO categoryDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:07
     * @Description 分类分页查询
     **/

    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:07
     * @Description 删除分类
     **/

    void deleteById(Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:07
     * @Description 修改分类
     **/

    void update(CategoryDTO categoryDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:07
     * @Description 启用/禁用分类
     **/

    void startOrStop(Integer status, Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:08
     * @Description 根据类型查询分类
     **/

    List<Category> list(Integer type);

}
