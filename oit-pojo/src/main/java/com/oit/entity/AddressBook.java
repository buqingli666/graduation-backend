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
@ApiModel(description = "地址簿实体类")
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地址id")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "收货人")
    private String consignee;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "性别 1 女 0 男")
    private String sex;

    @ApiModelProperty(value = "省级区划编号")
    private String provinceCode;

    @ApiModelProperty(value = "省级名称")
    private String provinceName;

    @ApiModelProperty(value = "市级区划编号")
    private String cityCode;

    @ApiModelProperty(value = "市级名称")
    private String cityName;

    @ApiModelProperty(value = "区级区划编号")
    private String districtCode;

    @ApiModelProperty(value = "区级名称")
    private String districtName;

    @ApiModelProperty(value = "详细地址")
    private String detail;

    @ApiModelProperty(value = "标签")
    private String label;

    @ApiModelProperty(value = "是否默认 0否 1是")
    private Integer isDefault;

}
