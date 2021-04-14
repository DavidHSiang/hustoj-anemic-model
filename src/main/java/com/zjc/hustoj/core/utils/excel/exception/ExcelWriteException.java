package com.zjc.hustoj.core.utils.excel.exception;

/**
 * Excel 读取异常
 *
 * @author David Hsiang
 * @date 2021/04/07/12:34 上午
 */
public class ExcelWriteException extends RuntimeException {

    private static final long serialVersionUID = 661909756599025481L;

    public ExcelWriteException() {
        super();
    }

    public ExcelWriteException(String message) {
        super(message);
    }

    public ExcelWriteException(String message, Throwable cause) {
        super(message, cause);
    }
}
