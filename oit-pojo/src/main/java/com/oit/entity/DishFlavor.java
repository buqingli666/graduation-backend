package com.oit.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "菜品口味实体类")
public class DishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜品口味id")
    private Long id;

    @ApiModelProperty(value = "菜品id")
    private Long dishId;

    @ApiModelProperty(value = "口味名称")
    private String name;

    @ApiModelProperty(value = "口味数据集合")
    private String value;

}
