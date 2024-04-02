package com.oit.service;

import com.oit.dto.OrdersSubmitDTO;
import com.oit.vo.OrderSubmitVO;

/**
 * @Author: buqingli
 * @Date: 2024/04/02/14:41
 * @Description: 订单管理服务层
 */

public interface OrderService {

    /*
     * @Author buqingli
     * @Date 2024/4/2 14:43
     * @Description 用户下单
     **/

    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

}
