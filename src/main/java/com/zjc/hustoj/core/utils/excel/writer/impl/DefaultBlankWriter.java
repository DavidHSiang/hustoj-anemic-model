package com.zjc.hustoj.core.utils.excel.writer.impl;

import com.zjc.hustoj.core.utils.excel.writer.CellWriter;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author David Hsiang
 * @date 2021/04/11/8:45 下午
 */
public class DefaultBlankWriter implements CellWriter {
    @Override
    public void write(Cell cell, Object data) {
        cell.setCellValue("");
        cell.setCellType(Cell.CELL_TYPE_BLANK);
    }
}
