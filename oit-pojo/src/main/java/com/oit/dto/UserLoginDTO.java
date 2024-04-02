package com.oit.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "C端用户登录传递的数据模型")
public class UserLoginDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "授权码")
    private String code;

}
