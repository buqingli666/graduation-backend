package com.oit.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "订单统计视图对象")
public class OrderStatisticsVO implements Serializable {

    @ApiModelProperty(value = "待接单数量")
    private Integer toBeConfirmed;

    @ApiModelProperty(value = "待派送数量")
    private Integer confirmed;

    @ApiModelProperty(value = "派送中数量")
    private Integer deliveryInProgress;

}
