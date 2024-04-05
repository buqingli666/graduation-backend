package com.oit.controller.admin;

import com.oit.result.Result;
import com.oit.service.WorkspaceService;
import com.oit.vo.BusinessDataVO;
import com.oit.vo.DishOverViewVO;
import com.oit.vo.OrderOverViewVO;
import com.oit.vo.SetmealOverViewVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;

/*
 * @Author buqingli
 * @Date 2024/4/5 17:27
 * @Description 工作台管理控制层
 **/

@RestController
@RequestMapping(value = "/admin/workspace", produces = "application/json; charset=utf-8")
@Slf4j
@Api(tags = "工作台相关接口")
public class WorkSpaceController {

    @Autowired
    private WorkspaceService workspaceService;

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:28
     * @Description 工作台今日数据查询
     **/

    @GetMapping("/businessData")
    @ApiOperation("工作台今日数据查询")
    public Result<BusinessDataVO> businessData() {
        //获得当天的开始时间
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);
        //获得当天的结束时间
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);
        BusinessDataVO businessDataVO = workspaceService.getBusinessData(begin, end);
        return Result.success(businessDataVO);
    }

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:31
     * @Description 查询订单管理数据
     **/

    @GetMapping("/overviewOrders")
    @ApiOperation("查询订单管理数据")
    public Result<OrderOverViewVO> orderOverView() {
        return Result.success(workspaceService.getOrderOverView());
    }

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:32
     * @Description 查询菜品总览
     **/

    @GetMapping("/overviewDishes")
    @ApiOperation("查询菜品总览")
    public Result<DishOverViewVO> dishOverView() {
        return Result.success(workspaceService.getDishOverView());
    }

    /*
     * @Author buqingli
     * @Date 2024/4/5 17:33
     * @Description 查询套餐总览
     **/

    @GetMapping("/overviewSetmeals")
    @ApiOperation("查询套餐总览")
    public Result<SetmealOverViewVO> setmealOverView() {
        return Result.success(workspaceService.getSetmealOverView());
    }
}
