package com.oit.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "购物车实体类")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车id")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "菜品id")
    private Long dishId;

    @ApiModelProperty(value = "套餐id")
    private Long setmealId;

    @ApiModelProperty(value = "口味")
    private String dishFlavor;

    @ApiModelProperty(value = "数量")
    private Integer number;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "图片路径")
    private String image;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
