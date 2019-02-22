package com.intendentapp.generator;

import com.intendentapp.dtomodel.ConsumerEntity;
import com.intendentapp.insert.InsertToSaleExcelReport;
import com.intendentapp.model.SaleReport;
import com.intendentapp.staticclasses.FilePaths;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenerateSaleReport {
	
	private final Logger log = LogManager.getLogger(GenerateSaleReport.class);
	
    private final String SALE_REPORT_FILE_PATH="src/main/webapp/static/xlsx/salerep.xlsx";

    private ConsumerEntity consumer;
    private SaleReport saleReport;
    private Integer message;                //zmienna sterująca czy potwierdza się stworzenie raportu czy wystąpił błąd
    private Date dateFromString;    //data przekonwertowana ze Stringa
    private SimpleDateFormat yearFormatter;
    private SimpleDateFormat monthFormatter;
    private String month;
    private String year;

    public GenerateSaleReport(){}

    public GenerateSaleReport(ConsumerEntity consumer, SaleReport saleReport) {
        this.consumer = consumer;
        this.saleReport = saleReport;
        this.yearFormatter = new SimpleDateFormat("yyyy");
        this.monthFormatter = new SimpleDateFormat("MMMM");
    }

    public Integer generate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        InsertToSaleExcelReport insertSale;
        
        try {
            dateFromString = formatter.parse(saleReport.getFilename());
        }catch (ParseException ex){
        	log.error("Błąd przy parsowaniu daty w zedstawieniu sprzedaży.");
            log.error(ex);
        }
        
        month = monthFormatter.format(dateFromString);
        year = yearFormatter.format(dateFromString);
        insertSale = new InsertToSaleExcelReport(consumer, saleReport, SALE_REPORT_FILE_PATH, FilePaths.SALES_FOLDER_PATH + month+year + "sale.xlsx");
        insertSale.createRows();
        insertSale.insertLp();
        insertSale.insertDate();
        insertSale.insertNameAndSurname();
        insertSale.insertAddress();
        insertSale.insertDueAmount();
        insertSale.insertWriteOff();
        insertSale.insertPayment();
        insertSale.insertExcess();
        insertSale.insertOverdue();
        insertSale.insertSums();
        message = insertSale.getOpenXlsx().save();
        return 0;
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
