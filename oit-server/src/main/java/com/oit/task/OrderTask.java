package com.oit.task;

import com.oit.entity.Orders;
import com.oit.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/04/05/9:59
 * @Description: 自定义定时任务，实现订单状态定时处理
 */

@Component
@Slf4j
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /*
     * @Author buqingli
     * @Date 2024/4/5 10:08
     * @Description 处理支付超时订单
     **/

    @Scheduled(cron = "0 * * * * ?") //每隔一分钟执行一次该任务
    public void processTimeoutOrder() {
        log.info("处理支付超时订单：{}", new Date());
        LocalDateTime time = LocalDateTime.now().plusMinutes(-15);
        // select * from orders where status = 1 and order_time < 当前时间-15分钟
        List<Orders> ordersList = orderMapper.getByStatusAndOrdertimeLT(Orders.PENDING_PAYMENT, time);
        if (ordersList != null && ordersList.size() > 0) {
            ordersList.forEach(order -> {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("支付超时，自动取消");
                order.setCancelTime(LocalDateTime.now());
                orderMapper.update(order);
            });
        }
    }

    /*
     * @Author buqingli
     * @Date 2024/4/5 10:08
     * @Description 处理"派送中"状态的订单
     **/

    @Scheduled(cron = "0 0 1 * * ?") //每天凌晨1点执行一次该任务
    public void processDeliveryOrder() {
        log.info("处理派送中订单：{}", new Date());
        // select * from orders where status = 4 and order_time < 当前时间-1小时
        LocalDateTime time = LocalDateTime.now().plusMinutes(-60);
        List<Orders> ordersList = orderMapper.getByStatusAndOrdertimeLT(Orders.DELIVERY_IN_PROGRESS, time);
        if (ordersList != null && ordersList.size() > 0) {
            ordersList.forEach(order -> {
                order.setStatus(Orders.COMPLETED);
                orderMapper.update(order);
            });
        }
    }

}