package com.sky.exception;

/*
 * @Author buqingli
 * @Date 2024/3/16 13:17
 * @Description 账号被锁定异常
 **/

public class AccountLockedException extends BaseException {

    public AccountLockedException() {
    }

    public AccountLockedException(String msg) {
        super(msg);
    }

}
