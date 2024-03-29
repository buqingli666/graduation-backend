package com.oit.dto;

import com.oit.entity.SetmealDish;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(description = "套餐管理传递的数据模型")
public class SetmealDTO implements Serializable {

    @ApiModelProperty(value = "套餐id")
    private Long id;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "套餐名称")
    private String name;

    @ApiModelProperty(value = "套餐价格")
    private BigDecimal price;

    @ApiModelProperty(value = "套餐状态 0:停用 1:启用")
    private Integer status;

    @ApiModelProperty(value = "套餐描述信息")
    private String description;

    @ApiModelProperty(value = "套餐图片路径")
    private String image;

    @ApiModelProperty(value = "套餐菜品关系")
    private List<SetmealDish> setmealDishes = new ArrayList<>();

}
