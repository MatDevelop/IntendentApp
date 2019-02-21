package com.intendentapp.generator;

import com.intendentapp.insert.InsertToDayExcelReport;
import com.intendentapp.insert.InsertToMonthExcelReport;

import java.io.File;
import java.text.SimpleDateFormat;

public class GenerateMonthReport {
    private final String FILENAME="src/main/webapp/static/xlsx/monthrep.xlsx";

    private InsertToDayExcelReport insertDayReport;
    private InsertToMonthExcelReport insertMonthReport;
    private Integer message;                //zmienna sterująca czy potwierdza się stworzenie raportu czy wystąpił błąd
    private SimpleDateFormat yearFormatter, monthFormatter;
    private String month, year;

    public GenerateMonthReport() {
    }

    public GenerateMonthReport(InsertToDayExcelReport insertDayReport) {
        this.insertDayReport = insertDayReport;
        yearFormatter = new SimpleDateFormat("yyyy");
        monthFormatter = new SimpleDateFormat("MMMM");
    }

    public Integer generate(){
        month=monthFormatter.format(insertDayReport.getDateFromString());
        year=yearFormatter.format(insertDayReport.getDateFromString());
        File f = new File("src/main/webapp/static/monthreports/"+month+year+".xlsx");
        if(f.exists()){
            insertMonthReport = new InsertToMonthExcelReport(insertDayReport,f.getAbsolutePath(), "src/main/webapp/static/monthreports/"+month+year+".xlsx");
            System.out.println(f.exists());
        }else{
            insertMonthReport = new InsertToMonthExcelReport(insertDayReport,FILENAME, "src/main/webapp/static/monthreports/"+month+year+".xlsx");
            System.out.println(month+" "+year);
        }

        //wstawienie nazwy miesiąca i roku do raportu miesięcznego
        insertMonthReport.insertMonthAndYear(month, year);
        message = insertMonthReport.insertRowDayReport();
        if(message!=1){     //błąd we wprowadzaniu wierszy
            insertMonthReport.getOpenXlsx().close();
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
