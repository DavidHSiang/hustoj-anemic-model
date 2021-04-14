package com.zjc.hustoj.core.utils.excel.model;

import com.google.common.collect.Lists;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/11/2:02 下午
 */
public class ExcelSheet<W> implements Iterable<ExcelRow> {

    private Sheet sheet;

    private Class<W> clazz;

    private ExcelSheet(Sheet sheet){
        this.sheet = sheet;
    }

    private ExcelSheet(Sheet sheet, Class<W> clazz) {
        this.sheet = sheet;
        this.clazz = clazz;
    }

    public static <W> ExcelSheet<W> create(Sheet sheet, Class<W> clazz) {
        return new ExcelSheet(sheet, clazz);
    }

    public static ExcelSheet create(Sheet sheet) {
        return new ExcelSheet(sheet);
    }

    public ExcelRow getRowAt( int index ){
        return ExcelRow.create(sheet.getRow(index));
    }



    /**
     * 在 sheet 的末尾追加一行
     */
    public void appendRow(W data) {
        ExcelRow row = this.createRow(this.sheet.getLastRowNum() + 1);
        row.write(data);
    }

    private ExcelRow createRow(int index) {
        Row row = this.sheet.createRow(index);
        return ExcelRow.create(row);
    }


    /**
     * 在 sheet 的末尾追加多行
     */
    public ExcelSheet appendRows(List<W> dataList){
        dataList.stream().forEach(data -> appendRow(data));
        return this;
    }

    public ExcelSheet appendTitle() {
//        Row row = sheet.createRow(0);
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            if (field.isAnnotationPresent(ExcelProperty.class)) {
//                ExcelProperty excelProperties = field.getAnnotation(ExcelProperty.class);
//                Cell cell = row.createCell(excelProperties.rowIndex());
//                cell.setCellType(Cell.CELL_TYPE_STRING);
//                cell.setCellValue(excelProperties.title());
//            }
//        }
        return this;
    }

    public File write() {
        return null;
    }

    public File write(String filePath) throws Exception{
        this.getTable().write(filePath);
        File file = new File(filePath);
        FileOutputStream fos = new FileOutputStream(file);

        Workbook workbook;
        if (ExcelTable.isExcel2003(filePath)) {
            //2003 版本的excel
            workbook = new HSSFWorkbook();
        } else if (ExcelTable.isExcel2007(filePath)) {
            //2007 版本的excel
            workbook = new XSSFWorkbook();
        } else {
            throw new Exception("file is not excel!");
        }
        // 1.创建 sheet
            // Sheet sheet = workbook.createSheet("sheet1");
        // 2.添加表头
            // titleRow(sheet, clazz);
        // 3.追加数据
            // appendRows(sheet, dataList);
        workbook.write(fos);
        return file;
    }

    private ExcelTable getTable() {
        return null;
    }

    /**
     * 读数据部分
     */
    public <R> List<R> read(Class<R> clazz) throws InstantiationException, IllegalAccessException {
        List<R> list = Lists.newArrayList();
        for (ExcelRow row : this) {
            R data = row.read(clazz);
            list.add(data);
        }
        return list;
    }

    public <R> List<R> read(int startIndex, Class<R> clazz) throws InstantiationException, IllegalAccessException {
        List<R> list = Lists.newArrayList();
        final Iterator<ExcelRow> iterator = this.iterator();
        for (; startIndex-- > 0 ;) {
            iterator.next();
        }
        while (iterator.hasNext()){
            ExcelRow row = iterator.next();
            R data = row.read(clazz);
            list.add(data);
        }
        return list;
    }


    /**
     * 迭代器部分
     */
    @Override
    public Iterator<ExcelRow> iterator() {
        return new SheetIterator();
    }

    private class SheetIterator implements Iterator<ExcelRow> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index <= sheet.getLastRowNum();
        }

        @Override
        public ExcelRow next() {
            return getRowAt( index ++ );
        }
    }
}
