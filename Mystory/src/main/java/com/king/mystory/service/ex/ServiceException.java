package com.king.mystory.service.ex;

/** 业务层异常的基类 throws new ServiceException ("业务层产生未知的异常")
 * 异常处理
 *   什么是异常处理 就是当用户使用的时候
 *   出现了异常 然后提示用户的
 *   继承异常SecurityException
 *   重写里面的方法
 */
public class ServiceException extends RuntimeException{
    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    protected ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
