package com.oit.service;

import com.oit.vo.OrderReportVO;
import com.oit.vo.SalesTop10ReportVO;
import com.oit.vo.TurnoverReportVO;
import com.oit.vo.UserReportVO;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 * @Author: buqingli
 * @Date: 2024/04/05/14:45
 * @Description: 报表管理服务层
 */

public interface ReportService {

    /*
     * @Author buqingli
     * @Date 2024/4/5 14:47
     * @Description 根据时间区间统计营业额
     **/

    TurnoverReportVO getTurnover(LocalDate begin, LocalDate end);

    /*
     * @Author buqingli
     * @Date 2024/4/5 16:33
     * @Description 根据时间区间统计用户数量
     **/

    UserReportVO getUserStatistics(LocalDate begin, LocalDate end);

    /*
     * @Author buqingli
     * @Date 2024/4/5 16:54
     * @Description 根据时间区间统计订单数量
     **/

    OrderReportVO getOrderStatistics(LocalDate begin, LocalDate end);

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:04
     * @Description 查询指定时间区间内的销量排名top10
     **/

    SalesTop10ReportVO getSalesTop10(LocalDate begin, LocalDate end);

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:51
     * @Description 导出近30天的运营数据报表
     **/

    void exportBusinessData(HttpServletResponse response);
}
