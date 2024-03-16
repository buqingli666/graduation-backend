package com.oit.exception;

/*
 * @Author buqingli
 * @Date 2024/3/16 13:17
 * @Description 业务异常
 **/

public class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }

}
