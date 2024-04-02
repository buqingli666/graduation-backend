package com.oit.controller.user;

import com.oit.dto.ShoppingCartDTO;
import com.oit.result.Result;
import com.oit.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
