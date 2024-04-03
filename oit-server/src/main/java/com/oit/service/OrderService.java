package com.oit.service;

import com.oit.dto.OrdersPaymentDTO;
import com.oit.dto.OrdersSubmitDTO;
import com.oit.vo.OrderPaymentVO;
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

    /*
     * @Author buqingli
     * @Date 2024/4/2 17:24
     * @Description 订单支付
     **/

    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /*
     * @Author buqingli
     * @Date 2024/4/2 17:24
     * @Description 支付成功，修改订单状态
     **/

    void paySuccess(String outTradeNo);
}
