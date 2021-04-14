package com.zjc.hustoj.core.utils.excel.writer.impl;

import com.zjc.hustoj.core.utils.excel.writer.CellWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/11/8:28 下午
 */
public class DefaultDateWriter implements CellWriter<Date> {
    @Override
    public void write(Cell cell, Date data) {
        Workbook workbook = cell.getRow().getSheet().getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        short format = workbook.createDataFormat().getFormat("yyyy-mm-dd HH:mm");
        cellStyle.setDataFormat(format);
        cell.setCellValue(data);
        cell.setCellStyle(cellStyle);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
    }
}
