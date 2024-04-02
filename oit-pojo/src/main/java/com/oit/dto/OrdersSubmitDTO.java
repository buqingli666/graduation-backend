package com.oit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "提交订单时传递的数据模型")
public class OrdersSubmitDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地址簿id")
    private Long addressBookId;

    @ApiModelProperty(value = "付款方式")
    private int payMethod;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "预计送达时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;

    @ApiModelProperty(value = "配送状态  1立即送出  0选择具体时间")
    private Integer deliveryStatus;

    @ApiModelProperty(value = "餐具数量")
    private Integer tablewareNumber;

    @ApiModelProperty(value = "餐具数量状态  1按餐量提供  0选择具体数量")
    private Integer tablewareStatus;

    @ApiModelProperty(value = "打包费")
    private Integer packAmount;

    @ApiModelProperty(value = "总金额")
    private BigDecimal amount;

}
