package com.oit.controller.user;

import com.oit.entity.Category;
import com.oit.result.Result;
import com.oit.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * @Author buqingli
 * @Date 2024/4/1 15:19
 * @Description C端-分类
 **/

@RestController("userCategoryController")
@RequestMapping(value = "/user/category", produces = "application/json; charset=utf-8")
@Api(tags = "C端-分类接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /*
     * @Author buqingli
     * @Date 2024/4/1 15:19
     * @Description 查询分类
     **/

    @GetMapping("/list")
    @ApiOperation("查询分类")
    public Result<List<Category>> list(Integer type) {
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }

}
