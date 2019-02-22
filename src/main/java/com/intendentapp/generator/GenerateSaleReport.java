package com.intendentapp.generator;

import com.intendentapp.dtomodel.ConsumerEntity;
import com.intendentapp.dtomodel.StatsEntity;
import com.intendentapp.insert.InsertToSaleExcelReport;
import com.intendentapp.model.SaleReport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenerateSaleReport {
	
	private final Logger log = LogManager.getLogger(GenerateSaleReport.class);
	
    private final String FILENAME="src/main/webapp/static/xlsx/salerep.xlsx";

    private ConsumerEntity consumer;
    private InsertToSaleExcelReport insertSale;
    private SaleReport saleReport;
    private Integer message;                //zmienna sterująca czy potwierdza się stworzenie raportu czy wystąpił błąd
    private Date dateFromString;    //data przekonwertowana ze Stringa
    private SimpleDateFormat yearFormatter, monthFormatter;
    private String month, year;

    public GenerateSaleReport(){}

    public GenerateSaleReport(ConsumerEntity consumer, SaleReport saleReport) {
        this.consumer = consumer;
        this.saleReport = saleReport;
        this.yearFormatter = new SimpleDateFormat("yyyy");
        this.monthFormatter = new SimpleDateFormat("MMMM");
    }

    public Integer generate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        
        try {
            dateFromString = formatter.parse(saleReport.getFilename());
        }catch (ParseException ex){
        	log.error("Błąd przy parsowaniu daty w zedstawieniu sprzedaży.");
            ex.printStackTrace();
        }
        
        month = monthFormatter.format(dateFromString);
        year = yearFormatter.format(dateFromString);
        insertSale = new InsertToSaleExcelReport(consumer, saleReport, FILENAME, "src/main/webapp/static/sales/" + month+year + "sale.xlsx");
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

    public StatsEntity getStatsEntity(List<StatsEntity> statsEntityList){
        if(statsEntityList.isEmpty()){
            StatsEntity statsEntity = new StatsEntity();
            statsEntity.setMonth_year(month+" "+year);
            statsEntity.setConsumers(insertSale.getNames().size());
            statsEntity.setReports(0);
            statsEntity.setUnpaid(insertSale.getUnpaid());
            return statsEntity;
        }else {
            StatsEntity statsEntity = statsEntityList.get(0);
            statsEntity.setConsumers(insertSale.getNames().size());
            statsEntity.setUnpaid(insertSale.getUnpaid());
            return statsEntity;
        }
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
