package com.oit.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "订单确认时传递的数据模型")
public class OrdersConfirmDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    private Long id;

    @ApiModelProperty(value = "订单状态 1待付款 2待接单 3 已接单 4 派送中 5 已完成 6 已取消 7 退款")
    private Integer status;

}
