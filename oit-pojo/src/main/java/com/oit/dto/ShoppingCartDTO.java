package com.oit.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "购物车传递的数据模型")
public class ShoppingCartDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜品id")
    private Long dishId;

    @ApiModelProperty(value = "套餐id")
    private Long setmealId;

    @ApiModelProperty(value = "菜品口味")
    private String dishFlavor;

}
