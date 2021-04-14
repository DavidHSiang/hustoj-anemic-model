package com.zjc.hustoj.core.utils.excel;

import com.google.common.collect.Lists;
import com.zjc.hustoj.core.utils.excel.model.ExcelSheet;
import com.zjc.hustoj.core.utils.excel.model.ExcelTable;
import com.zjc.hustoj.user.vo.RegisterInfoDTO;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/08/9:01 下午
 */
public class Demo {
    public static void main2(String[] args) throws Exception {
        // 对读取Excel表格标题测试
        String fileName = "/Users/david/Documents/testexcel.xlsx";

        List<RegisterInfoDTO> dataList = Lists.newArrayList();


        RegisterInfoDTO registerInfo1 = new RegisterInfoDTO();
        registerInfo1.setUserId("201702310301");
        registerInfo1.setNick("刘文程");
        registerInfo1.setPassword("123456");
        registerInfo1.setSchool("浙江工业大学之江学院");
        registerInfo1.setEmail("12345@163.com");

        RegisterInfoDTO registerInfo2 = new RegisterInfoDTO();
        registerInfo2.setUserId("201702310302");
        registerInfo2.setNick("祝高原");
        registerInfo2.setPassword("123456");
        registerInfo2.setSchool("浙江工业大学之江学院");
        registerInfo2.setEmail("12345@163.com");

        dataList.add(registerInfo1);
        dataList.add(registerInfo2);

        ExcelUtilsBak.writeExcel(fileName, dataList, RegisterInfoDTO.class);
        System.out.println("写入成功");
    }

    public static void main1(String[] args) throws Exception {
        String fileName = "/Users/david/Documents/testexcel.xlsx";
        FileInputStream fileInputStream = new FileInputStream(fileName);
        List<RegisterInfoDTO> list = ExcelUtilsBak.readExcelContent(fileInputStream,fileName,RegisterInfoDTO.class);
        for (RegisterInfoDTO registerInfoDTO : list) {
            System.out.println(registerInfoDTO);
        }
    }

    public static void main0(String[] args) throws Exception {
        String fileName = "/Users/david/Documents/testexcel.xlsx";
        InputStream fileInputStream = new FileInputStream(fileName);
        ExcelTable table = ExcelTable.create(fileInputStream);
        ExcelSheet sheet = table.getFirstSheet();
        List<RegisterInfoDTO> list = sheet.read(1, RegisterInfoDTO.class);
        list.forEach(System.out::println);

    }

    public static void main_1(String[] args) throws Exception {
        String fileName = "/Users/david/Documents/testexcel.xlsx";
        InputStream fileInputStream = new FileInputStream(fileName);
        ExcelTable table = ExcelTable.create(fileInputStream);
        List<RegisterInfoDTO> list = table.read(RegisterInfoDTO.class);
        list.forEach(System.out::println);


    }
    /**
     *
     * 理想状态：(读)                 一个 sheet 和 多个类 挂钩
     * ExcelTable.create(fileInputStream)
     *     .getFirstSheet()
     *     .read(C.class);
     *
     * ExcelTable.create(fileInputStream)
     *     .getFirstSheet()
     *     .read(B.class);
     * 理想状态：(写)                 一个 sheet 和 一个类 挂钩
     * ExcelTable.create()
     *          .createStreet(RegisterInfoDTO.class)
     *          .appendTitle()
     *          .appendRows(dataList)
     *          .write(filePath);
     * （OR）
     * ExcelTable.create()
     *          .createStreet("street1", RegisterInfoDTO.class)
     *          .appendTitle()
     *          .appendRows(dataList)
     *          .write(filePath);
     **/
    public static void main(String[] args) throws Exception {
        // ========================= INIT DATA =========================
        String filePath = "/Users/david/Documents/testexcel2.xlsx";
        List<RegisterInfoDTO> dataList = initData();

        /**
         * 单纯写
         */
        ExcelTable.create()
                .createStreet(RegisterInfoDTO.class)
                .appendTitle()
                .appendRows(dataList)
                .write(filePath);

        /**
         * 单纯读
         */
        InputStream inputStream = new FileInputStream(filePath);
        ExcelTable.create(inputStream)
                .getFirstSheet()
                .read(RegisterInfoDTO.class);

        /**
         * 先读后写
         */
        ExcelTable.create(inputStream)
                .getFirstSheet()
                .appendRows(dataList);

        ExcelSheet<RegisterInfoDTO> excelSheet = ExcelTable.create(inputStream).getFirstSheet(RegisterInfoDTO.class);
        excelSheet.read(RegisterInfoDTO.class);
        excelSheet.appendRows(dataList);


        System.out.println("写入成功");
    }

    private static List<RegisterInfoDTO> initData(){
        List<RegisterInfoDTO> dataList = Lists.newArrayList();

        RegisterInfoDTO registerInfo1 = new RegisterInfoDTO();
        registerInfo1.setUserId("201702310301");
        registerInfo1.setNick("刘文程");
        registerInfo1.setPassword("123456");
        registerInfo1.setSchool("浙江工业大学之江学院");
        registerInfo1.setEmail("12345@163.com");

        RegisterInfoDTO registerInfo2 = new RegisterInfoDTO();
        registerInfo2.setUserId("201702310302");
        registerInfo2.setNick("祝高原");
        registerInfo2.setPassword("123456");
        registerInfo2.setSchool("浙江工业大学之江学院");
        registerInfo2.setEmail("12345@163.com");

        dataList.add(registerInfo1);
        dataList.add(registerInfo2);
        return dataList;
    }
}
