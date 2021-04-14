package com.zjc.hustoj.core.utils.excel.writer.impl;

import com.zjc.hustoj.core.utils.excel.writer.CellWriter;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author David Hsiang
 * @date 2021/04/11/8:43 下午
 */
public class DefaultStringWriter implements CellWriter<String> {
    @Override
    public void write(Cell cell, String data) {
        cell.setCellValue(data);
        cell.setCellType(Cell.CELL_TYPE_STRING);
    }
}
