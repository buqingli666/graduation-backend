package com.oit.exception;

/*
 * @Author buqingli
 * @Date 2024/3/16 13:18
 * @Description 套餐启用失败异常
 **/

public class SetmealEnableFailedException extends BaseException {

    public SetmealEnableFailedException(){}

    public SetmealEnableFailedException(String msg){
        super(msg);
    }
}
