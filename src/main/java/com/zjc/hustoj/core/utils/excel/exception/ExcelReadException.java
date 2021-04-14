package com.zjc.hustoj.core.utils.excel.exception;

/**
 * Excel 读取异常
 *
 * @author David Hsiang
 * @date 2021/04/07/12:34 上午
 */
public class ExcelReadException extends RuntimeException {

    private static final long serialVersionUID = 5361274623020431960L;

    public ExcelReadException() {
        super();
    }

    public ExcelReadException(String message) {
        super(message);
    }

    public ExcelReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
