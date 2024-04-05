package com.oit.service.impl;

import com.oit.entity.Orders;
import com.oit.mapper.OrderMapper;
import com.oit.service.ReportService;
import com.oit.vo.TurnoverReportVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: buqingli
 * @Date: 2024/04/05/14:45
 * @Description: 报表管理业务层
 */

@Service
@Slf4j
public class ReportServiceImpl implements ReportService {

    @Autowired
    private OrderMapper orderMapper;

    /*
     * @Author buqingli
     * @Date 2024/4/5 14:48
     * @Description 根据时间区间统计营业额
     **/

    @Override
    public TurnoverReportVO getTurnover(LocalDate begin, LocalDate end) {
        //该集合用于存放从begin到end范围内的每天日期
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(begin);
        while (!begin.equals(end)) {
            //日期计算，获得指定日期后1天的日期
            begin = begin.plusDays(1);
            dateList.add(begin);
        }
        //该集合用于存放每天的营业额
        List<Double> turnoverList = new ArrayList<>();
        for (LocalDate date : dateList) {
            //查询date日期对应的营业额，营业额是指状态为"已完成"的订单的总金额
            LocalDateTime beginTime = LocalDateTime.of(date, LocalTime.MIN);
            LocalDateTime endTime = LocalDateTime.of(date, LocalTime.MAX);
            //select sum(amount) from orders where order_time > beginTime and order_time < endTime and status = 5
            Double turnover = orderMapper.sumByMap(beginTime, endTime, Orders.COMPLETED);
            turnover = turnover == null ? 0.0 : turnover;
            turnoverList.add(turnover);
        }
        String dateListStr = StringUtils.join(dateList, ",");
        String turnoverListStr = StringUtils.join(turnoverList, ",");
        //数据封装
        return TurnoverReportVO.builder()
                .dateList(dateListStr)
                .turnoverList(turnoverListStr)
                .build();
    }
}
