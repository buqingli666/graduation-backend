package com.oit.service;

import com.oit.vo.TurnoverReportVO;

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
}
