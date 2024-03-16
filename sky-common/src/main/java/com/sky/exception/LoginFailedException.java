package com.sky.exception;

/*
 * @Author buqingli
 * @Date 2024/3/16 13:18
 * @Description 登录失败
 **/

public class LoginFailedException extends BaseException{
    public LoginFailedException(String msg){
        super(msg);
    }
}
