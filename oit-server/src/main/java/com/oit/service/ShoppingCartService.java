package com.oit.service;

import com.oit.dto.ShoppingCartDTO;
import com.oit.entity.ShoppingCart;

import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/04/02/8:43
 * @Description: 购物车服务层
 */
public interface ShoppingCartService {

    /*
     * @Author buqingli
     * @Date 2024/4/2 8:50
     * @Description 添加购物车
     **/

    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /*
     * @Author buqingli
     * @Date 2024/4/2 10:06
     * @Description 查看购物车
     **/

    List<ShoppingCart> showShoppingCart();
}
