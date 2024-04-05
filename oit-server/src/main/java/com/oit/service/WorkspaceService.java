package com.oit.service;

import com.oit.vo.BusinessDataVO;
import com.oit.vo.DishOverViewVO;
import com.oit.vo.OrderOverViewVO;
import com.oit.vo.SetmealOverViewVO;

import java.time.LocalDateTime;

/*
 * @Author buqingli
 * @Date 2024/4/5 17:35
 * @Description 工作台管理服务层
 **/

public interface WorkspaceService {

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:35
     * @Description 根据时间段统计营业数据
     **/

    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:36
     * @Description 查询订单管理数据
     **/

    OrderOverViewVO getOrderOverView();

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:36
     * @Description 查询菜品总览
     **/

    DishOverViewVO getDishOverView();

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:36
     * @Description 查询套餐总览
     **/

    SetmealOverViewVO getSetmealOverView();

}
