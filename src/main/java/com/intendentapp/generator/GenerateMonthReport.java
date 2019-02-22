package com.intendentapp.generator;

import com.intendentapp.insert.InsertToDayExcelReport;
import com.intendentapp.insert.InsertToMonthExcelReport;
import com.intendentapp.staticclasses.FilePaths;

import java.io.File;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenerateMonthReport {
	
	private final Logger log = LogManager.getLogger(GenerateMonthReport.class);
	
    private final String MONTH_REPORT_TEMPLATE_FILE_PATH = "src/main/webapp/static/xlsx/monthrep.xlsx";

    private InsertToDayExcelReport insertDayReport;
    private Integer message;                //zmienna sterująca czy potwierdza się stworzenie raportu czy wystąpił błąd
    private SimpleDateFormat yearFormatter;
    private SimpleDateFormat monthFormatter;
    private String month;
    private String year;

    public GenerateMonthReport() {}

    public GenerateMonthReport(InsertToDayExcelReport insertDayReport) {
        this.insertDayReport = insertDayReport;
        yearFormatter = new SimpleDateFormat("yyyy");
        monthFormatter = new SimpleDateFormat("MMMM");
    }

    public Integer generate(){
    	InsertToMonthExcelReport insertMonthReport;
        month = monthFormatter.format(insertDayReport.getDateFromString());
        year = yearFormatter.format(insertDayReport.getDateFromString());
        File f = new File(FilePaths.MONTH_REPORTS_FOLDER_PATH + month + year + ".xlsx");
        if(f.exists()){
            insertMonthReport = new InsertToMonthExcelReport(insertDayReport, f.getAbsolutePath(),
            		FilePaths.MONTH_REPORTS_FOLDER_PATH + month + year + ".xlsx");
        }else{
            insertMonthReport = new InsertToMonthExcelReport(insertDayReport, MONTH_REPORT_TEMPLATE_FILE_PATH,
            		FilePaths.MONTH_REPORTS_FOLDER_PATH + month + year + ".xlsx");
        }

        //wstawienie nazwy miesiąca i roku do raportu miesięcznego
        insertMonthReport.insertMonthAndYear(month, year);
        message = insertMonthReport.insertRowDayReport();
        if(message != 1){     //błąd we wprowadzaniu wierszy
            insertMonthReport.getOpenXlsx().close();
            log.error("Błąd przy wprowadzaniu wierszy w raporcie miesięcznym.");
            return message;
        }
        return insertMonthReport.getOpenXlsx().save();
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public Integer getMessage() {
        return message;
    }
}
