package com.sky.exception;

/*
 * @Author buqingli
 * @Date 2024/3/16 13:23
 * @Description 不允许删除异常
 **/

public class DeletionNotAllowedException extends BaseException {

    public DeletionNotAllowedException(String msg) {
        super(msg);
    }

}
