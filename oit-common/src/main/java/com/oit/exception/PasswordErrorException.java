package com.oit.exception;

/*
 * @Author buqingli
 * @Date 2024/3/16 13:18
 * @Description 密码错误异常
 **/

public class PasswordErrorException extends BaseException {

    public PasswordErrorException() {
    }

    public PasswordErrorException(String msg) {
        super(msg);
    }

}
