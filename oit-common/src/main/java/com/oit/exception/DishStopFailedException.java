package com.oit.exception;

/**
 * @Author: buqingli
 * @Date: 2024/03/28/17:16
 * @Description: 菜品停售失败异常
 */
public class DishStopFailedException extends BaseException {

    public DishStopFailedException() {
    }

    public DishStopFailedException(String msg) {
        super(msg);
    }

}
