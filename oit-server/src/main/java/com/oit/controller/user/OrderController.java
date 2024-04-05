package com.oit.controller.user;

import com.oit.dto.OrdersPaymentDTO;
import com.oit.dto.OrdersSubmitDTO;
import com.oit.result.PageResult;
import com.oit.result.Result;
import com.oit.service.OrderService;
import com.oit.vo.OrderPaymentVO;
import com.oit.vo.OrderSubmitVO;
import com.oit.vo.OrderVO;
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

    /*
     * @Author buqingli
     * @Date 2024/4/3 12:00
     * @Description 历史订单查询 status 订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消
     **/

    @GetMapping("/historyOrders")
    @ApiOperation("历史订单查询")
    public Result<PageResult> page(int page, int pageSize, Integer status) {
        PageResult pageResult = orderService.pageQuery4User(page, pageSize, status);
        return Result.success(pageResult);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/3 12:31
     * @Description 查询订单详情
     **/

    @GetMapping("/orderDetail/{id}")
    @ApiOperation("查询订单详情")
    public Result<OrderVO> details(@PathVariable("id") Long id) {
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/3 12:42
     * @Description 用户取消订单
     **/

    @PutMapping("/cancel/{id}")
    @ApiOperation("取消订单")
    public Result<?> cancel(@PathVariable("id") Long id) throws Exception {
        orderService.userCancelById(id);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/4/3 13:04
     * @Description 再来一单
     **/

    @PostMapping("/repetition/{id}")
    @ApiOperation("再来一单")
    public Result<?> repetition(@PathVariable Long id) {
        orderService.repetition(id);
        return Result.success();
    }

    /*
     * @Author buqingli
     * @Date 2024/4/5 14:13
     * @Description 用户催单
     **/

    @GetMapping("/reminder/{id}")
    @ApiOperation("用户催单")
    public Result<?> reminder(@PathVariable("id") Long id) {
        orderService.reminder(id);
        return Result.success();
    }

}
