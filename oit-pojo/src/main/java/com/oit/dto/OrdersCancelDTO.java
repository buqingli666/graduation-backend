package com.oit.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "订单取消时传递的数据模型")
public class OrdersCancelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    private Long id;

    @ApiModelProperty(value = "订单取消原因")
    private String cancelReason;

}
