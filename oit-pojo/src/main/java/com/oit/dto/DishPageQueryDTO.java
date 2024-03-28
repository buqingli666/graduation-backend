package com.oit.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "菜品管理分页查询时传递的数据模型")
public class DishPageQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    private int page;

    @ApiModelProperty(value = "每页显示记录数")
    private int pageSize;

    @ApiModelProperty(value = "菜品名称")
    private String name;

    @ApiModelProperty(value = "分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "菜品状态 0表示禁用 1表示启用")
    private Integer status;

}
