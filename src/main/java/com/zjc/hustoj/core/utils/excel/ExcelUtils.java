//package com.zjc.hustoj.core.utils.excel;
//
//import com.google.common.collect.Lists;
//import com.zjc.hustoj.core.exception.ServiceException;
//import com.zjc.hustoj.core.utils.excel.annotation.ExcelProperty;
//import com.zjc.hustoj.core.utils.excel.exception.ExcelReadException;
//import org.apache.commons.collections4.CollectionUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.math.NumberUtils;
//import org.apache.commons.lang3.time.DateUtils;
//import org.apache.poi.hssf.usermodel.HSSFDateUtil;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.ValidationException;
//import javax.validation.Validator;
//import java.io.*;
//import java.lang.reflect.Field;
//import java.text.DecimalFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//import static com.zjc.hustoj.core.utils.excel.ExcelUtilsBak.readRow;
//
///**
// * @author David Hsiang
// * @date 2021/04/07/10:55 下午
// */
//public class ExcelUtils {
//    /**
//     * 日期格式化
//     */
//    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm");
//
//    /**
//     * 整数格式化,取所有整数部分
//     */
//    private static final DecimalFormat df = new DecimalFormat("#");
//
//
//
//
//    /**
//     * 读取Excel表格表头的内容
//     *
//     * @param inputStream
//     * @return String 表头内容的数组
//     */
//    public static List<String> readExcelTitle(InputStream inputStream, String fileName) throws Exception {
//        List<String> titles = Lists.newArrayList();
//        Workbook workbook = getWorkbook(inputStream, fileName);
//        if (workbook == null) {
//            return titles;
//        }
//        Sheet sheet = workbook.getSheetAt(0);
//        //excel为空
//        if (sheet.getLastRowNum() == 0 && sheet.getPhysicalNumberOfRows() == 0) {
//            return titles;
//        }
//        //得到首行的row
//        Row row = sheet.getRow(0);
//        //标题总列数
//        int colNum = row.getPhysicalNumberOfCells();
//        for (int i = 0; i < colNum; i++) {
//            titles.add(getStringCellValue(row.getCell(i)));
//        }
//        return titles;
//    }
//
//    private static boolean isBlankRow(int colNum, Row row) {
//        for (int i = 0; i <= colNum; i++) {
//            // 只要有一列不为空, 该行就不为空行
//            if (StringUtils.isNotBlank(getCellStringValue(row.getCell(i)))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//
//
//
//
//    public static <T> List<T> readExcelContent(InputStream inputStream, String fileName, Class<T> clazz) throws Exception {
//        Workbook workbook = getWorkbook(inputStream, fileName);
//        if (workbook == null) {
//            return Lists.newArrayList();
//        }
//        Sheet sheet = workbook.getSheetAt(0);
//        return readSheet(sheet,clazz);
//    }
//
//    /**
//     * 获取单元格数据内容为字符串类型的数据
//     *
//     * @param cell Excel单元格
//     * @return String 单元格数据内容
//     */
//    private static String getStringCellValue(Cell cell) {
//        String cellValue = StringUtils.EMPTY;
//        if (cell != null) {
//            cellValue = cell.getStringCellValue().trim();
//        }
//        return cellValue;
//    }
//
//    /**
//     * 是否是2003的excel，返回true是2003
//     */
//    public static boolean isExcel2003(String filePath) {
//        return filePath.matches("^.+\\.(?i)(xls)$");
//    }
//
//    /**
//     * 是否是2007的excel，返回true是2007
//     */
//    public static boolean isExcel2007(String filePath) {
//        return filePath.matches("^.+\\.(?i)(xlsx)$");
//    }
//
//    /**
//     * 写入 excel
//     */
//    public static <T> File writeExcel(String filePath, List<T> dataList, Class<T> clazz) throws Exception {
//
//        if (CollectionUtils.isEmpty(dataList)) {
//            return new File(filePath);
//        }
//        File file = new File(filePath);
//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(file);
//            Workbook workbook = createWorkbook(filePath);
//            Sheet sheet = workbook.createSheet("sheet1");
//
//            // excel 表头
//            titleRow(sheet, clazz);
//            // 追加数据
//            appendRows(sheet, dataList);
//
//            workbook.write(fos);
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return file;
//    }
//
//
//
//    /**
//     * 将数据写入单元格
//     */
//    private static void writeCellValue(Cell cell, String value, Class fieldType) throws Exception {
//        try {
//            if (Integer.class == fieldType || Long.class == fieldType) {
//                double d = NumberUtils.toDouble(String.valueOf(value));
//                cell.setCellValue(d);
//                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//            } else if (Double.class == fieldType || Float.class == fieldType) {
//                double d = NumberUtils.toDouble(String.valueOf(value));
//                cell.setCellValue(d);
//                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//            } else if (Date.class == fieldType) {
//                Workbook workbook = cell.getRow().getSheet().getWorkbook();
//                CellStyle cellStyle = workbook.createCellStyle();
//                short format = workbook.createDataFormat().getFormat("yyyy-mm-dd HH:mm");
//                cellStyle.setDataFormat(format);
//                Date date = DateUtils.parseDate(value, "yyyy-mm-dd HH:mm");
//                cell.setCellValue(date);
//                cell.setCellStyle(cellStyle);
//                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
//            } else if (String.class == fieldType) {
//                cell.setCellValue(value);
//                cell.setCellType(Cell.CELL_TYPE_STRING);
//            } else if (Boolean.class == fieldType) {
//                cell.setCellValue(Boolean.valueOf(value));
//                cell.setCellType(Cell.CELL_TYPE_BOOLEAN);
//            } else {
//                cell.setCellValue("");
//                cell.setCellType(Cell.CELL_TYPE_BLANK);
//            }
//        } catch (Exception e) {
//            throw new ServiceException("invalid cell value format");
//        }
//
//    }
//
//
//}
//
