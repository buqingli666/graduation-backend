package com.oit.vo;

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
@ApiModel(description = "菜品总览视图对象")
public class DishOverViewVO implements Serializable {

    @ApiModelProperty(value = "已启售数量")
    private Integer sold;

    @ApiModelProperty(value = "已停售数量")
    private Integer discontinued;

}
