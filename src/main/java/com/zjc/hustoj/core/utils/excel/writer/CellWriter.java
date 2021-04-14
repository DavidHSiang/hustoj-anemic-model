package com.zjc.hustoj.core.utils.excel.writer;

import org.apache.poi.ss.usermodel.Cell;

/**
 * @author David Hsiang
 * @date 2021/04/11/8:28 下午
 */
public interface CellWriter<T> {
    void write(Cell cell, T data);
}
