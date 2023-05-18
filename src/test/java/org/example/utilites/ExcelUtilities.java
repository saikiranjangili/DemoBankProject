package org.example.utilites;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtilities {

    public static FileInputStream fis;
    public static FileOutputStream fos;
    public static XSSFWorkbook workbook;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;

    //path = TestBase.path;
    static String path = "src/test/java/org/example/testData/Login_Data.xlsx";
    static String Cuspath = "src/test/java/org/example/testData/Customer_Data.xlsx";

    //    private final Object sheetname = null;
//
//    // String Sheetname = "";
//    public ExcelUtilities(String Sheet){
//        Object sheet1 = this.sheetname;
//
//    }
    public static String excelData(String sheetname, String cellname) throws IOException {


        fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetname);
        String cellvalue = null;

        int rowcount = sheet.getPhysicalNumberOfRows();
        int cellcount = sheet.getRow(1).getLastCellNum();

        for (int i = 1; i <= rowcount; i++) {

            for (int j = 0; j < cellcount; j++) {
                String value = row.getCell(j).getStringCellValue();
                if (value.equals(cellname)) {
                    cellvalue = row.getCell(j + 1).toString();
                }
            }
        }
        return cellvalue;
    }

    public int getRowCount(String sheetname) throws IOException {

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetname);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fis.close();
        return rowcount;
    }

    public int getCellCount(String sheetname, int rownum) throws IOException {

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetname);
        row = sheet.getRow(rownum);
        int cellcount = row.getLastCellNum();
        workbook.close();
        fis.close();
        return cellcount;
    }

    public String getCellData(String sheetname, int rownum, int cellnum) throws IOException {

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetname);
        row = sheet.getRow(rownum);
        cell = row.getCell(cellnum);

        DataFormatter formatter = new DataFormatter();
        String data;

        try {
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = " ";
        }

        workbook.close();
        fis.close();
        return data;
    }

    public void setCellData(String sheetname, int rownum, int cellnum, String data) throws IOException {

        fis = new FileInputStream(Cuspath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetname);
        row = sheet.getRow(rownum);
        // cell = row.getCell(cellnum);
        cell = row.createCell(cellnum);
        cell.setCellValue(data);
        fis.close();

        fos = new FileOutputStream(Cuspath);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();

    }

}

