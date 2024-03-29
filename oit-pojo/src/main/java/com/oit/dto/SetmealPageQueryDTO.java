package com.oit.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "套餐分页查询时传递的数据模型")
public class SetmealPageQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    private int page;

    @ApiModelProperty(value = "每页显示记录数")
    private int pageSize;

    @ApiModelProperty(value = "套餐名称")
    private String name;

    @ApiModelProperty(value = "分类id")
    private Integer categoryId;

    @ApiModelProperty(value = "套餐状态 0表示禁用 1表示启用")
    private Integer status;

}
