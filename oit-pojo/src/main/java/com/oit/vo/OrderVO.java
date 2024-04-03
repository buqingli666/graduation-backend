package com.oit.vo;

import com.oit.entity.OrderDetail;
import com.oit.entity.Orders;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "订单试图对象")
public class OrderVO extends Orders implements Serializable {

    @ApiModelProperty(value = "订单菜品信息")
    private String orderDishes;

    @ApiModelProperty(value = "订单详情")
    private List<OrderDetail> orderDetailList;

}
