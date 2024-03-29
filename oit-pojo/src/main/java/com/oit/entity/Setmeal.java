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
@ApiModel(description = "套餐实体类")
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "修改人")
    private Long updateUser;

}
