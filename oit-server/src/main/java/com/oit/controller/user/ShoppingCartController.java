package com.oit.controller.user;

import com.oit.dto.ShoppingCartDTO;
import com.oit.entity.ShoppingCart;
import com.oit.result.Result;
import com.oit.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @Author buqingli
 * @Date 2024/4/2 8:42
 * @Description: 购物车控制层
 **/

@RestController
@RequestMapping(value = "/user/shoppingCart", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "C端-购物车相关接口")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /*
     * @Author buqingli
     * @Date 2024/4/2 8:50
     * @Description 添加购物车
     **/

    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public Result<String> add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("添加购物车,商品信息为:{}", shoppingCartDTO);
        shoppingCartService.addShoppingCart(shoppingCartDTO);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/4/2 10:05
     * @Description 查看购物车
     **/

    @GetMapping("/list")
    @ApiOperation("查看购物车")
    public Result<List<ShoppingCart>> list() {
        List<ShoppingCart> shoppingCartList = shoppingCartService.showShoppingCart();
        return Result.success(shoppingCartList);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/2 10:26
     * @Description 清空购物车
     **/

    @DeleteMapping("/clean")
    @ApiOperation("清空购物车商品")
    public Result<String> clean() {
        shoppingCartService.cleanShoppingCart();
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/4/2 10:34
     * @Description 删除购物车中单个商品
     **/

    @PostMapping("/sub")
    @ApiOperation("删除购物车中单个商品")
    public Result<String> sub(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("删除购物车中单个商品,商品:{}", shoppingCartDTO);
        shoppingCartService.subShoppingCart(shoppingCartDTO);
        return Result.success();
    }
}
