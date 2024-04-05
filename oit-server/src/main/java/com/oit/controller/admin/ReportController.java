package com.oit.controller.admin;

import com.oit.result.Result;
import com.oit.service.ReportService;
import com.oit.vo.OrderReportVO;
import com.oit.vo.SalesTop10ReportVO;
import com.oit.vo.TurnoverReportVO;
import com.oit.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @Author: buqingli
 * @Date: 2024/04/05/14:41
 * @Description: 报表管理控制层
 */

@RestController
@RequestMapping(value = "/admin/report", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "统计报表相关接口")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /*
     * @Author buqingli
     * @Date 2024/4/5 14:43
     * @Description 营业额数据统计
     **/

    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额数据统计")
    public Result<TurnoverReportVO> turnoverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate end) {
        log.info("营业额数据统计,开始时间:{},结束时间:{}", begin, end);
        return Result.success(reportService.getTurnover(begin, end));
    }

    /*
     * @Author buqingli
     * @Date 2024/4/5 16:33
     * @Description 用户数据统计
     **/

    @GetMapping("/userStatistics")
    @ApiOperation("用户数据统计")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("用户数据统计,开始时间:{},结束时间:{}", begin, end);
        return Result.success(reportService.getUserStatistics(begin, end));
    }

    /*
     * @Author buqingli
     * @Date 2024/4/5 16:53
     * @Description 订单数据统计
     **/

    @GetMapping("/ordersStatistics")
    @ApiOperation("订单数据统计")
    public Result<OrderReportVO> orderStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            LocalDate end) {
        log.info("订单数据统计,开始时间:{},结束时间:{}", begin, end);
        return Result.success(reportService.getOrderStatistics(begin, end));
    }

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:04
     * @Description 销量排名统计
     **/

    @GetMapping("/top10")
    @ApiOperation("销量排名统计")
    public Result<SalesTop10ReportVO> top10(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("销量排名统计,开始时间:{},结束时间:{}", begin, end);
        return Result.success(reportService.getSalesTop10(begin, end));
    }
}
