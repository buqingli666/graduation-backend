package com.oit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.oit.constant.MessageConstant;
import com.oit.constant.StatusConstant;
import com.oit.dto.CategoryDTO;
import com.oit.dto.CategoryPageQueryDTO;
import com.oit.entity.Category;
import com.oit.exception.DeletionNotAllowedException;
import com.oit.mapper.CategoryMapper;
import com.oit.mapper.DishMapper;
import com.oit.mapper.SetmealMapper;
import com.oit.result.PageResult;
import com.oit.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @Author buqingli
 * @Date 2024/3/14 16:08
 * @Description 分类管理业务层
 **/

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:10
     * @Description 新增分类
     **/

    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        //属性拷贝
        BeanUtils.copyProperties(categoryDTO, category);
        //分类状态默认为禁用状态0
        category.setStatus(StatusConstant.DISABLE);
        //设置创建时间、修改时间、创建人、修改人
        //category.setCreateTime(LocalDateTime.now());
        //category.setUpdateTime(LocalDateTime.now());
        //category.setCreateUser(BaseContext.getCurrentId());
        //category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.insert(category);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:11
     * @Description 分类分页查询
     **/

    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(),categoryPageQueryDTO.getPageSize());
        //下一条sql进行分页，自动加入limit关键字分页
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:11
     * @Description 删除分类
     **/

    public void deleteById(Long id) {
        //查询当前分类是否关联了菜品，如果关联了就抛出业务异常
        Integer count = dishMapper.countByCategoryId(id);
        if(count > 0){
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }
        //查询当前分类是否关联了套餐，如果关联了就抛出业务异常
        count = setmealMapper.countByCategoryId(id);
        if(count > 0){
            //当前分类下有菜品，不能删除
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        //删除分类数据
        categoryMapper.deleteById(id);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:11
     * @Description 修改分类
     **/

    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO,category);
        //设置修改时间、修改人
        //category.setUpdateTime(LocalDateTime.now());
        //category.setUpdateUser(BaseContext.getCurrentId());
        categoryMapper.update(category);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:11
     * @Description 启用/禁用分类
     **/

    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .id(id)
                .status(status)
                //.updateTime(LocalDateTime.now())
                //.updateUser(BaseContext.getCurrentId())
                .build();
        categoryMapper.update(category);
    }

    /*
     * @Author buqingli
     * @Date 2024/3/14 16:11
     * @Description 根据类型查询分类
     **/

    public List<Category> list(Integer type) {
        return categoryMapper.list(type);
    }
}
