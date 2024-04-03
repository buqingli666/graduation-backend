package com.oit.controller.admin;

import com.oit.dto.OrdersPageQueryDTO;
import com.oit.result.PageResult;
import com.oit.result.Result;
import com.oit.service.OrderService;
import com.oit.vo.OrderStatisticsVO;
import com.oit.vo.OrderVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: buqingli
 * @Date: 2024/04/03/14:00
 * @Description: 管理端订单管理控制层
 */

@RestController("adminOrderController")
@RequestMapping(value = "/admin/order", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "订单管理相关接口")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /*
     * @Author buqingli
     * @Date 2024/4/3 14:06
     * @Description 订单搜索
     **/

    @GetMapping("/conditionSearch")
    @ApiOperation("订单搜索")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/3 14:43
     * @Description 各个状态的订单数量统计
     **/

    @GetMapping("/statistics")
    @ApiOperation("各个状态的订单数量统计")
    public Result<OrderStatisticsVO> statistics() {
        OrderStatisticsVO orderStatisticsVO = orderService.statistics();
        return Result.success(orderStatisticsVO);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/3 14:57
     * @Description 订单详情
     **/

    @GetMapping("/details/{id}")
    @ApiOperation("查询订单详情")
    public Result<OrderVO> details(@PathVariable("id") Long id) {
        OrderVO orderVO = orderService.details(id);
        return Result.success(orderVO);
    }
}
