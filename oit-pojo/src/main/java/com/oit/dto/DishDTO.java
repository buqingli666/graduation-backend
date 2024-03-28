package com.oit.dto;

import com.oit.entity.DishFlavor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "新增菜品时传递的数据模型")
public class DishDTO implements Serializable {

    @ApiModelProperty(value = "菜品id")
    private Long id;

    @ApiModelProperty(value = "菜品名称")
    private String name;

    @ApiModelProperty(value = "菜品分类id")
    private Long categoryId;

    @ApiModelProperty(value = "菜品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "菜品图片路径")
    private String image;

    @ApiModelProperty(value = "菜品描述信息")
    private String description;

    @ApiModelProperty(value = "菜品状态: 0 停售 1 起售")
    private Integer status;

    @ApiModelProperty(value = "菜品口味集合")
    private List<DishFlavor> flavors = new ArrayList<>();

}
