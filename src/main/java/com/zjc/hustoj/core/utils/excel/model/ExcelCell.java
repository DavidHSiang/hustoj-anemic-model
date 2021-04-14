package com.zjc.hustoj.core.utils.excel.model;

import com.zjc.hustoj.core.utils.excel.exception.ExcelReadException;
import com.zjc.hustoj.core.utils.excel.writer.*;
import com.zjc.hustoj.core.utils.excel.writer.WriterFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

/**
 * @author David Hsiang
 * @date 2021/04/11/2:01 下午
 */
public class ExcelCell {

    private Cell cell;

    public static ExcelCell create(Cell cell) {
        return new ExcelCell(cell);
    }

    public ExcelCell(Cell cell) {
        this.cell = cell;
    }

    public Object read(){
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString().trim();
            case Cell.CELL_TYPE_NUMERIC:
                if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    return cell.getDateCellValue();
                }
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                }
                return cell.getNumericCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue();
            case Cell.CELL_TYPE_BLANK:
            default:
                return StringUtils.EMPTY;
        }
    }

    public <T> T read(Class<T> clazz){
        Object cellData = this.read();
        if (!clazz.isAssignableFrom(cellData.getClass())){
            throw new ExcelReadException();
        }
        return (T) cellData;
    }

    public void write(Object data) {
        CellWriter cellWriter = WriterFactory.getByData(data);
        cellWriter.write(cell, data);
    }
    
}
