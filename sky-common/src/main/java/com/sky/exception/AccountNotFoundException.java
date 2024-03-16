package com.sky.exception;

/*
 * @Author buqingli
 * @Date 2024/3/16 13:17
 * @Description 账号不存在异常
 **/

public class AccountNotFoundException extends BaseException {

    public AccountNotFoundException() {
    }

    public AccountNotFoundException(String msg) {
        super(msg);
    }

}
