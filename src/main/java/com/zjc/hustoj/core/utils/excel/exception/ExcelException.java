package com.zjc.hustoj.core.utils.excel.exception;

/**
 * @author David Hsiang
 * @date 2021/04/08/9:12 下午
 */
public class ExcelException extends RuntimeException {

    private static final long serialVersionUID = 3352592451783004812L;

    public ExcelException() {
        super();
    }

    public ExcelException(String message) {
        super(message);
    }

    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}