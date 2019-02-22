package com.intendentapp.insert;

import com.intendentapp.configuration.OpenXlsx;
import com.intendentapp.dtomodel.ConsumerEntity;
import com.intendentapp.model.SaleReport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InsertToSaleExcelReport {
	
	private final Logger log = LogManager.getLogger(InsertToSaleExcelReport.class);
	
    private SaleReport saleReport;
    private OpenXlsx openXlsx;      //instancja pliku excel
    private XSSFWorkbook workbook;  //instacja skoroszytu
    private XSSFSheet sheet;        //instancja arkuszu w skoroszycie
    private XSSFCellStyle style1;
    private XSSFCellStyle style2;
    private List<String> names;
    private List<String> surnames;
    private List<String> addresses;
    private Integer unpaid;

    //pobranie sheta i workbooka z openxlsx, wstawienie  styli z projektu test, zamiana nulli w listach na 0.00, wstawianie wierszy
    public InsertToSaleExcelReport(){}

    public InsertToSaleExcelReport(ConsumerEntity consumer, SaleReport saleReport, String filename, String outfilename){
        this.saleReport = saleReport;
        openXlsx = new OpenXlsx(filename, outfilename);     //utworzenie obiektu pliku excelowego
        workbook = openXlsx.getWorkbook();
        sheet = openXlsx.getSheet();
        style1 = workbook.createCellStyle();
        style2 = workbook.createCellStyle();
        setStyles();
        names = new LinkedList<>(Arrays.asList(consumer.getName().split(",")));
        surnames = new LinkedList<>(Arrays.asList(consumer.getSurname().split(",")));
        addresses = new LinkedList<>(Arrays.asList(consumer.getAddress().split(",")));
        unpaid=0;

    }

    public void createRows(){
        int i=1;
        for(String name : names){	//zrobic zwykłego fora z i=1 i przetestować
            int rows = sheet.getLastRowNum();
            sheet.shiftRows(i, rows, 1);
            sheet.createRow(i);
            Row row = sheet.getRow(i);
            row.setHeight((short) 500);
            for(int j=0; j<=9; j++){
                row.createCell(j);
                if(j>=0 && j<=3){
                    row.getCell(j).setCellStyle(style1);
                }else if(j>=4 && j<=8){
                    row.getCell(j).setCellStyle(style2);
                }
                if(j == 9){
                	//TODO zmienić wszędzie generowanie formuł z poziomu kodu
                    String strFormula= "SUM(G" + String.valueOf(row.getRowNum() + 1) + ":I" + String.valueOf(row.getRowNum() + 1) + ")";
                    row.getCell(j).setCellType(XSSFCell.CELL_TYPE_FORMULA);
                    row.getCell(j).setCellFormula(strFormula);
                    row.getCell(j).setCellStyle(style2);
                }
            }
            i++;
        }
    }

    public void insertLp(){
        int i = 1;
        for(String name : names){	//TODO zrobić fora
            openXlsx.updateIntegerCell(i,0,i);
            i++;
        }
    }

    public void insertDate(){
        int i = 1;
        for(String date : saleReport.getDate()){
            openXlsx.updateStringCell(i, 1, date);
            i++;
        }
    }

    public void insertNameAndSurname(){
        int i = 1;
        for(int j=0; j<names.size(); j++){
            openXlsx.updateStringCell(i, 2, names.get(j) + " " + surnames.get(j));
            i++;
        }
    }

    public void insertAddress(){
        int i = 1;
        for(String address : addresses){
            openXlsx.updateStringCell(i, 3, address);
            i++;
        }
    }
    //wstawienie kwoty należnej
    public void insertDueAmount(){
        int i = 1;
        for(Double dueamount : saleReport.getDueamount()){
            if(dueamount == null){
                openXlsx.updateStringCell(i, 4, "");
            }else {
                openXlsx.updateDoubleCell(i, 4, dueamount);
            }
            i++;
        }
    }
    //wstawianie odpisu
    public void insertWriteOff(){
        int i = 1;
        for(Double writeoff : saleReport.getWriteoff()){
            if(writeoff == null){
                openXlsx.updateStringCell(i, 5, "");
            }else {
                openXlsx.updateDoubleCell(i, 5, writeoff);
            }
            i++;
        }
    }
    //wstawianie wpłaty
    public void insertPayment(){
        int i = 1;
        for(Double payment : saleReport.getPayment()){
            if(payment == null){
                openXlsx.updateStringCell(i, 6, "");
                unpaid += 1;
            }else {
                openXlsx.updateDoubleCell(i, 6, payment);
            }
            i++;
        }
    }
    //wstawianie nadpłaty
    public void insertExcess(){
        int i = 1;
        for(Double excess : saleReport.getExcess()){
            if(excess == null){
                openXlsx.updateStringCell(i, 7, "");
            }else {
                openXlsx.updateDoubleCell(i, 7, excess);
            }
            i++;
        }
    }
    //wstawianie wpłaty zaległej
    public void insertOverdue(){
        int i = 1;
        for(Double overdue : saleReport.getOverdue()){
            if(overdue == null){
                openXlsx.updateStringCell(i, 8, "");
            }else {
                openXlsx.updateDoubleCell(i, 8, overdue);
            }
            i++;
        }
    }
    //wstawianie sum
    public void insertSums(){
        int rows = sheet.getLastRowNum();
        String strFormula2 = "SUM(E2:E" + String.valueOf(rows) + ")";
        String strFormula3 = "SUM(F2:F" + String.valueOf(rows) + ")";
        String strFormula4 = "SUM(G2:G" + String.valueOf(rows) + ")";
        String strFormula5 = "SUM(H2:H" + String.valueOf(rows) + ")";
        String strFormula6 = "SUM(I2:I" + String.valueOf(rows) + ")";
        String strFormula7 = "SUM(J2:J" + String.valueOf(rows) + ")";

        sheet.getRow(rows).getCell(4).setCellFormula(strFormula2);
        sheet.getRow(rows).getCell(4).setCellStyle(style2);

        sheet.getRow(rows).getCell(5).setCellFormula(strFormula3);
        sheet.getRow(rows).getCell(5).setCellStyle(style2);

        sheet.getRow(rows).getCell(6).setCellFormula(strFormula4);
        sheet.getRow(rows).getCell(6).setCellStyle(style2);

        sheet.getRow(rows).getCell(7).setCellFormula(strFormula5);
        sheet.getRow(rows).getCell(7).setCellStyle(style2);

        sheet.getRow(rows).getCell(8).setCellFormula(strFormula6);
        sheet.getRow(rows).getCell(8).setCellStyle(style2);

        sheet.getRow(rows).getCell(9).setCellFormula(strFormula7);
        sheet.getRow(rows).getCell(9).setCellStyle(style2);
    }



    public void setStyles(){
        style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        style2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(XSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style1.setDataFormat(workbook.createDataFormat().getFormat("@"));
        style2.setDataFormat(workbook.createDataFormat().getFormat("0.00"));
    }

    public Integer getUnpaid() {
        return unpaid;
    }

    public List<String> getNames() {
        return names;
    }

    public OpenXlsx getOpenXlsx() {
        return openXlsx;
    }
}
