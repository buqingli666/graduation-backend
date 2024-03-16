package com.sky.exception;

/*
 * @Author buqingli
 * @Date 2024/3/16 13:25
 * @Description 用户未登录异常
 **/

public class UserNotLoginException extends BaseException {

    public UserNotLoginException() {
    }

    public UserNotLoginException(String msg) {
        super(msg);
    }

}
