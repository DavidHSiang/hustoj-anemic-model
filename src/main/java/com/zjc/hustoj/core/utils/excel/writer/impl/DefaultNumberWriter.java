package com.zjc.hustoj.core.utils.excel.writer.impl;

import com.zjc.hustoj.core.utils.excel.writer.CellWriter;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author David Hsiang
 * @date 2021/04/11/8:28 下午
 */
public class DefaultNumberWriter implements CellWriter<Number> {
    @Override
    public void write(Cell cell, Number data) {
        double dValue = NumberUtils.toDouble(data.toString());
        cell.setCellValue(dValue);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
    }
}
