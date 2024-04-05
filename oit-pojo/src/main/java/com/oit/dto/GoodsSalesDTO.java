package com.oit.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "查询商品销量传递的数据模型")
public class GoodsSalesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "销量")
    private Integer number;
}
