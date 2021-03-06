package com.intendentapp.insert;

import com.intendentapp.configuration.OpenXlsx;

import java.text.DecimalFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsertToMonthExcelReport {

	private final Logger log = LogManager.getLogger(InsertToMonthExcelReport.class);
	
    private OpenXlsx openXlsx;      //instancja pliku excel
    private InsertToDayExcelReport insertDayReport;
    
    public InsertToMonthExcelReport() {}

    public InsertToMonthExcelReport(InsertToDayExcelReport insertDayReport, String filename, String outFilename) {
    	this.openXlsx = new OpenXlsx(filename, outFilename);     //utworzenie obiektu pliku excelowego;
        this.insertDayReport = insertDayReport;
    }

    public void insertMonthAndYear(String month, String year){
        openXlsx.updateStringCell(4, 4, "za miesiąc " + month + " " + year + " r.");
    }
    
    public void insertMonthRepCells(int i){
        DecimalFormat df = new java.text.DecimalFormat("0.00");
        openXlsx.updateIntegerCell(i, 0, i - 9);    //dodanie LP
        openXlsx.updateStringCell(i, 1, insertDayReport.getDate());
        openXlsx.updateStringCell(i, 2, insertDayReport.getRepNumber());
        openXlsx.updateDoubleCell(i, 3, Double.parseDouble(df.format(insertDayReport.getReportCost()).replace(",",".")));
        openXlsx.updateIntegerCell(i, 4, insertDayReport.getStudentsSum());
        openXlsx.updateStringCell(i, 5, "-");
        if(insertDayReport.getNauczyciele().isEmpty()){
            openXlsx.updateStringCell(i, 6, "-");
        }else {
            openXlsx.updateIntegerCell(i, 6, Integer.parseInt(insertDayReport.getNauczyciele()));
        }
        openXlsx.updateIntegerCell(i, 7, insertDayReport.getPersonsSum());
        openXlsx.updateDoubleCell(i, 8, Double.parseDouble(df.format(insertDayReport.getAvgCost()).replace(",",".")));
    }

    public Integer insertRowDayReport(){
        for(int i = 10; i < 33; i++){
            if(!openXlsx.readOneCell(i, 1).isEmpty()){
                if(openXlsx.readOneCell(i, 1).equals(insertDayReport.getDate())){
                    insertMonthRepCells(i);
                    log.info("Zmodyfikowano poprawnie raport dzienny w raporcie miesięcznym.");
                    return 1; //wszystko ok
                }
            }else{
                insertMonthRepCells(i);
                log.info("Dodano poprawnie raport dzienny do raportu miesięcznego.");
                return 1;
            }
            if(i == 32){
            	log.error("Przekroczono liczbę wierszy dostępnych w raporcie miesięcznym");
                return 3;   //za mało wierszy w raporcie miesięcznym    
            }
        }   
        return 0;
    }

    public OpenXlsx getOpenXlsx() {
        return openXlsx;
    }
}
