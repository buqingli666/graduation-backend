package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CategoryMapper {

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:43
     * @Description 新增分类
     **/

    void insert(Category category);

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:44
     * @Description 分页查询
     **/

    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:45
     * @Description 根据id删除分类
     **/

    void deleteById(Long id);

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:46
     * @Description 根据id修改分类
     **/

    void update(Category category);

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:47
     * @Description 根据类型查询分类
     **/

    List<Category> list(Integer type);
}
