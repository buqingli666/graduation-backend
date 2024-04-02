package com.oit.mapper;

import com.oit.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    /*
     * @Author buqingli
     * @Date 2024/4/2 14:57
     * @Description 批量插入订单明细数据
     **/

    void insertBatch(List<OrderDetail> orderDetailList);
}
