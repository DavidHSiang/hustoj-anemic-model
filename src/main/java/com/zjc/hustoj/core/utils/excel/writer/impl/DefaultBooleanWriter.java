package com.zjc.hustoj.core.utils.excel.writer.impl;

import com.zjc.hustoj.core.utils.excel.writer.CellWriter;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author David Hsiang
 * @date 2021/04/11/8:44 下午
 */
public class DefaultBooleanWriter implements CellWriter<Boolean> {
    @Override
    public void write(Cell cell, Boolean data) {
        cell.setCellValue(data);
        cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
    }
}
