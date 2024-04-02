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
}
