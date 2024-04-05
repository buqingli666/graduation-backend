package com.oit.mapper;

import com.github.pagehelper.Page;
import com.oit.dto.OrdersPageQueryDTO;
import com.oit.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {

    /*
     * @Author buqingli
     * @Date 2024/4/2 14:48
     * @Description 插入订单数据
     **/

    void insert(Orders order);

    /*
     * @Author buqingli
     * @Date 2024/4/2 17:28
     * @Description 根据订单号和用户id查询订单
     **/

    Orders getByNumberAndUserId(@Param("orderNumber") String orderNumber,
                                @Param("userId") Long userId);

    /*
     * @Author buqingli
     * @Date 2024/4/2 17:28
     * @Description 修改订单信息
     **/

    void update(Orders orders);

    /*
     * @Author buqingli
     * @Date 2024/4/3 12:12
     * @Description 分页条件查询并按下单时间排序
     **/

    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);

    /*
     * @Author buqingli
     * @Date 2024/4/3 12:33
     * @Description 根据id查询订单
     **/

    Orders getById(Long id);

    /*
     * @Author buqingli
     * @Date 2024/4/3 14:48
     * @Description 根据状态统计订单数量
     **/

    Integer countStatus(Integer status);

    /*
     * @Author buqingli
     * @Date 2024/4/5 10:32
     * @Description 根据状态和下单时间查询订单
     **/

    List<Orders> getByStatusAndOrdertimeLT(@Param("status") Integer status,
                                           @Param("orderTime") LocalDateTime orderTime);

    /*
     * @Author buqingli
     * @Date 2024/4/5 14:50
     * @Description 根据动态条件统计营业额
     **/

    Double sumByMap(@Param("beginTime") LocalDateTime beginTime,
                    @Param("endTime") LocalDateTime endTime,
                    @Param("status") Integer status);

}
