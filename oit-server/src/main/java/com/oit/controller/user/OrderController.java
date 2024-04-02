package com.oit.controller.user;

import com.oit.dto.OrdersSubmitDTO;
import com.oit.result.Result;
import com.oit.service.OrderService;
import com.oit.vo.OrderSubmitVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
