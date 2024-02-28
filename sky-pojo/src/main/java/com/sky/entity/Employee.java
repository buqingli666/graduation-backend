package com.sky.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("员工实体类")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("员工id")
    private Long id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("身份证")
    private String idNumber;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("创建时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("修改人")
    private Long updateUser;

}
