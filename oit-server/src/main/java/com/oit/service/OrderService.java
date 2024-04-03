package com.oit.service;

import com.oit.dto.*;
import com.oit.result.PageResult;
import com.oit.vo.OrderPaymentVO;
import com.oit.vo.OrderStatisticsVO;
import com.oit.vo.OrderSubmitVO;
import com.oit.vo.OrderVO;

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

    /*
     * @Author buqingli
     * @Date 2024/4/3 12:01
     * @Description 用户端订单分页查询
     **/

    PageResult pageQuery4User(int page, int pageSize, Integer status);

    /*
     * @Author buqingli
     * @Date 2024/4/3 12:31
     * @Description 查询订单详情
     **/

    OrderVO details(Long id);

    /*
     * @Author buqingli
     * @Date 2024/4/3 12:43
     * @Description 用户取消订单
     **/

    void userCancelById(Long id) throws Exception;

    /*
     * @Author buqingli
     * @Date 2024/4/3 13:05
     * @Description 再来一单
     **/

    void repetition(Long id);

    /*
     * @Author buqingli
     * @Date 2024/4/3 14:06
     * @Description 条件搜索订单
     **/

    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /*
     * @Author buqingli
     * @Date 2024/4/3 14:46
     * @Description 各个状态的订单数量统计
     **/

    OrderStatisticsVO statistics();

    /*
     * @Author buqingli
     * @Date 2024/4/3 15:04
     * @Description 接单
     **/

    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /*
     * @Author buqingli
     * @Date 2024/4/3 15:07
     * @Description 拒单
     **/

    void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception;
}
