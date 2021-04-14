package com.zjc.hustoj.core.utils.excel.model;

import com.google.common.collect.Lists;
import com.zjc.hustoj.user.vo.RegisterInfoDTO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author David Hsiang
 * @date 2021/04/11/2:06 下午
 */
public class ExcelTable implements Iterable<ExcelSheet> {

    private Workbook workbook;

    private static final String DEF_SHEET_NAME_PRE = "sheet";

    /**
     * 构造器
     */
    private ExcelTable(Workbook workbook) {
        this.workbook = workbook;
    }

    public ExcelTable() {

    }

    /**
     * 公共方法
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * ExcelSheet 创建器
     */
    public ExcelSheet<Map> createStreet(String sheetName){
        Sheet sheet = this.workbook.createSheet(sheetName);
        return ExcelSheet.create(sheet, Map.class);
    }

    public <T> ExcelSheet<T> createStreet(Class<T> clazz) {
        int newSheetNum = workbook.getNumberOfSheets() + 1;
        Sheet sheet = this.workbook.createSheet(DEF_SHEET_NAME_PRE + newSheetNum);
        return ExcelSheet.create(sheet, clazz);
    }

    public <T> ExcelSheet<T> createStreet(String sheetName, Class<T> clazz){
        Sheet sheet = this.workbook.createSheet(sheetName);
        return ExcelSheet.create(sheet, clazz);
    }

    public ExcelSheet getSheetAt(int index){
        return ExcelSheet.create(workbook.getSheetAt(index));
    }

    public <W> ExcelSheet<W> getSheetAt(int index, Class<W> clazz){
        return ExcelSheet.create(workbook.getSheetAt(index), clazz);
    }

    public ExcelSheet getFirstSheet() {
        return this.getSheetAt(0);
    }

    public <W> ExcelSheet<W> getFirstSheet(Class<W> clazz) {
        return this.getSheetAt(0, clazz);
    }

    /**
     * get&set
     */
    public Workbook getWorkbook() {
        return this.workbook;
    }


    /**
     * 写数据部分
     */
    public File write(String filePath){
        return null;
    }

    /**
     * ExcelTable 创建器
     */
    public static ExcelTable create(InputStream inputStream) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(inputStream);
        return create(workbook);
    }

    public static ExcelTable create(Workbook workbook)  {
        return new ExcelTable(workbook);
    }

    public static ExcelTable create() {
        return new ExcelTable();
    }

    /**
     * 读数据部分
     */
    public <R> List<R> read(Class<R> clazz) throws IllegalAccessException, InstantiationException {
        List<R> list = Lists.newArrayList();
        for (ExcelSheet sheet : this) {
            List<R> listFor1Sheet = sheet.read(clazz);
            list.addAll(listFor1Sheet);
        }
        return list;
    }

    /**
     * 迭代器部分
     */
    @Override
    public Iterator<ExcelSheet> iterator() {
        return new TableIterator();
    }

    private class TableIterator implements Iterator<ExcelSheet> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < workbook.getNumberOfSheets();
        }

        @Override
        public ExcelSheet next() {
            return getSheetAt( index ++ );
        }
    }
}
