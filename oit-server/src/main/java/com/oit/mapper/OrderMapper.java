package com.oit.mapper;

import com.oit.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {

    /*
     * @Author buqingli
     * @Date 2024/4/2 14:48
     * @Description 插入订单数据
     **/

    void insert(Orders order);

    /*
     * @Author buqingli
     * @Date 2024/4/2 17:28
     * @Description 根据订单号和用户id查询订单
     **/

    Orders getByNumberAndUserId(String orderNumber, Long userId);

    /*
     * @Author buqingli
     * @Date 2024/4/2 17:28
     * @Description 修改订单信息
     **/

    void update(Orders orders);


}
