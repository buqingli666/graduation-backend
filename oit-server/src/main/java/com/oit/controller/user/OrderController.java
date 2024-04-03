package com.oit.controller.user;

import com.oit.dto.OrdersPaymentDTO;
import com.oit.dto.OrdersSubmitDTO;
import com.oit.result.Result;
import com.oit.service.OrderService;
import com.oit.vo.OrderPaymentVO;
import com.oit.vo.OrderSubmitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: buqingli
 * @Date: 2024/04/02/14:37
 * @Description: C端-订单管理控制层
 */

@RestController("userOrderController")
@RequestMapping(value = "/user/order", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "C端-订单接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*
     * @Author buqingli
     * @Date 2024/4/2 14:40
     * @Description 用户下单
     **/

    @PostMapping("/submit")
    @ApiOperation("用户下单")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO ordersSubmitDTO) {
        log.info("用户下单:{}", ordersSubmitDTO);
        OrderSubmitVO orderSubmitVO = orderService.submitOrder(ordersSubmitDTO);
        return Result.success(orderSubmitVO);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/2 17:13
     * @Description 订单支付
     **/

    @PutMapping("/payment")
    @ApiOperation("订单支付")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO ordersPaymentDTO) throws Exception {
        log.info("订单支付:{}", ordersPaymentDTO);
        OrderPaymentVO orderPaymentVO = orderService.payment(ordersPaymentDTO);
        log.info("生成预支付交易单:{}", orderPaymentVO);

        //--------------模拟交易成功，修改数据库订单状态--------------
        orderService.paySuccess(ordersPaymentDTO.getOrderNumber());
        log.info("模拟交易成功:{}", ordersPaymentDTO.getOrderNumber());
        //--------------模拟交易成功，修改数据库订单状态--------------

        return Result.success(orderPaymentVO);
    }

}
