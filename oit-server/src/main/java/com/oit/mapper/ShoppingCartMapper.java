package com.oit.mapper;

import com.oit.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {

    /*
     * @Author buqingli
     * @Date 2024/4/2 8:56
     * @Description 条件查询
     **/

    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /*
     * @Author buqingli
     * @Date 2024/4/2 8:57
     * @Description 更新商品数量
     **/

    void updateNumberById(ShoppingCart shoppingCart);

    /*
     * @Author buqingli
     * @Date 2024/4/2 8:58
     * @Description 插入购物车数据
     **/

    void insert(ShoppingCart shoppingCart);

}
