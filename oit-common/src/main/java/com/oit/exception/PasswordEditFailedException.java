package com.oit.exception;

/*
 * @Author buqingli
 * @Date 2024/3/16 13:18
 * @Description 密码修改失败异常
 **/

public class PasswordEditFailedException extends BaseException{

    public PasswordEditFailedException(String msg){
        super(msg);
    }

}
