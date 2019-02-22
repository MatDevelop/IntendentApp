package com.intendentapp.generator;

import com.intendentapp.model.DayReport;
import com.intendentapp.converter.DayReportConverter;
import com.intendentapp.dtomodel.ProductEntity;
import com.intendentapp.insert.InsertToDayExcelReport;

import java.text.ParseException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenerateDayReport {
	
	private final Logger log = LogManager.getLogger(GenerateDayReport.class);
	
    private final String DAY_REPORT_TEMPLATE_FILE_PATH="src/main/webapp/static/xlsx/dayrep.xlsx";
   
    private DayReport dayReport;
    private Integer message, message2;                //zmienna sterująca czy potwierdza się stworzenie raportu czy wystąpił błąd
    private InsertToDayExcelReport insert;
    private GenerateMonthReport generateMonthReport;

    public GenerateDayReport(){}

    public GenerateDayReport(DayReport dayReport) {
        this.insert = new InsertToDayExcelReport(dayReport, DAY_REPORT_TEMPLATE_FILE_PATH);
        this.insert.setProductUnitAmountAndValues(dayReport.getProduct(), dayReport.getUnitprice(), dayReport.getAmount(), dayReport.getPositionValue(), dayReport.getNumber());
        this.dayReport = dayReport;

    }

    public void generate(List<ProductEntity> productEntityList){
        //sprawdzenie czy wprowadzono wszystkie wartości w każdym wierszu. Nie może być sytuacji gdzie podano nazwę a nie podano ceny lub ilości i odwrotnie
        if((insert.getProducts().size() == insert.getUnitprices().size()) &&
                (insert.getProducts().size() == insert.getAmounts().size()) &&
                (insert.getUnitprices().size() == insert.getAmounts().size()) &&
                (insert.getPositionValues().size() == insert.getProducts().size())){
            //wprowadzenie daty do arkusza
            try {
                insert.insertDate(dayReport.getDate());
            } catch (ParseException e) {
                log.error("Błąd parsowania daty przy generowaniu raportu dziennego.");
            	e.printStackTrace();
            }
            //--------------------------
            //wprowadzenie numeru raportu do arkusza
            insert.insertRepNumber(dayReport.getReportNumber());
            //--------------------------
            //wprowadzenie obiadu do arkusza
            insert.insertDinner(dayReport.getDinner1(), dayReport.getDinner2(), dayReport.getDinner3(), dayReport.getDinner4());
            //--------------------------
            //wprowadzenie liczby uczni do arkusza
            insert.insertPodstawowka(dayReport.getPodstawowa());
            insert.insertPrzedszkole(dayReport.getPrzedszkole());
            insert.insertZerowka(dayReport.getZerowka());
            //--------------------------
            //wprowadzenie liczby personelu do arkusza
            insert.insertNauczyciele(dayReport.getNauczyciele());
            //--------------------------
            //wprowadzenie produktów
            insert.insertProducts(productEntityList);
            //wprowadzenie sumy osób i wartości produktów oraz wyliczenie średniej
            insert.insertSums();
            message = insert.getOpenXlsx().save();
            if(message != 1){
                message2 = 0;
            }else{
                generateMonthReport = new GenerateMonthReport(insert);
                message2=generateMonthReport.generate();
            }

        }else{
            message=2;
        }

    }

    public GenerateMonthReport getGenerateMonthReport() {
        return generateMonthReport;
    }

    public InsertToDayExcelReport getInsert() {
        return insert;
    }

    public Integer getMessage() {
        return message;
    }
    
    public Integer getMessage2() {
        return message2;
    }
}
