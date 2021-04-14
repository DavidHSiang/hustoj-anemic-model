package com.zjc.hustoj.core.exception;

/**
 * Service层公用的Exception.
 *
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 *
 * @author David Hsiang
 * @date 2021/04/07/12:34 上午
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1401593546385403720L;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}