package com.sky.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "分类管理分页查询时传递的数据模型")
public class CategoryPageQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页码")
    private int page;

    @ApiModelProperty(value = "每页显示记录数")
    private int pageSize;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类类型: 1菜品分类 2套餐分类")
    private Integer type;

}
