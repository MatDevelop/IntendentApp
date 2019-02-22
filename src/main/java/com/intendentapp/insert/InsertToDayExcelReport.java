package com.intendentapp.insert;

import com.intendentapp.model.DayReport;
import com.intendentapp.configuration.OpenXlsx;
import com.intendentapp.dtomodel.ProductEntity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class InsertToDayExcelReport {
	
	private final Logger log = LogManager.getLogger(InsertToDayExcelReport.class);
	
    private int productRowNumber;   //zmienna zawierająca numer wiersza do wprowadzenia produktu
    private List<String> products;         //Lista zawierająca nazwy produktów
    private List<String> unitprices;       //Lista zawierająca cenu jednostkowe produktów
    private List<String> amounts;          //Lista zawierająca ilości poszczególnych produktów
    private List<String> positionValues;
    private List<String> numbers;
    private Date dateFromString;    //data przekonwertowana ze Stringa
    private String date;            //zmienna z datą pobraną od użytkownika
    private OpenXlsx openXlsx;      //instancja pliku excel
    private String dinner1;         //pozycja 1 obiadu w arkuszu
    private String dinner2;         //pozycja 2 obiadu w arkuszu
    private String dinner3;         //pozycja 3 obiadu w arkuszu
    private String dinner4;         //pozycja 4 obiadu w arkuszu
    private Integer personsSum;     //suma wszystkich osoób
    private Integer studentsSum;
    private Double reportCost;     //suma wartości w raporcie
    private String repNumber;		//numer raportu
    private Double avgCost;			//średni koszt obiadu
    private String nauczyciele;
    private String podzialPodstawowa;
    private String podzialPrzedszkole;
    private String podzialZerowka;

    public InsertToDayExcelReport(){}

    public InsertToDayExcelReport(DayReport dayReport, String filename){
        this.productRowNumber = 16;
        openXlsx = new OpenXlsx(filename, "src/main/webapp/static/reports/" + dayReport.getDate() + ".xlsx");     //utworzenie obiektu pliku excelowego
        date = dayReport.getDate();
        this.dinner1 = "1 ";
        this.dinner2 = "2 ";
        this.dinner3 = "3 ";
        this.dinner4 = "4 ";
        this.personsSum = 0;
        this.studentsSum = 0;
        this.reportCost = 0.0;
        this.repNumber = dayReport.getReportNumber();
        this.nauczyciele = dayReport.getNauczyciele();
        this.podzialPodstawowa = dayReport.getPodzialPodstawowa();
        this.podzialPrzedszkole = dayReport.getPodzialPrzedszkole();
        this.podzialZerowka = dayReport.getPodzialZerowka();

    }

    public void setProductUnitAmountAndValues(String product, String unitprices, String amounts, String positionValues, String number){
        this.products = new LinkedList<>(Arrays.asList(product.split(",")));
        //usunięcie pustych wartości z listy produktów
        while (products.contains("...")){	//TODO zrobić jak niżej
            products.remove("...");
        }
        this.unitprices = new LinkedList<>(Arrays.asList(unitprices.split(",")));
        this.unitprices.removeAll(Arrays.asList("",null));
        this.amounts = new LinkedList<>(Arrays.asList(amounts.split(",")));
        this.amounts.removeAll(Arrays.asList("",null));
        this.positionValues = new LinkedList<>(Arrays.asList(positionValues.split(",")));
        this.positionValues.removeAll(Arrays.asList("",null));
        this.numbers = new LinkedList<>(Arrays.asList(number.split(",")));
        this.numbers.removeAll(Arrays.asList("",null));
    }

    public void insertDate(String date) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("EEEE");
        dateFromString = formatter.parse(date);         //konwersja ze String na Date
        openXlsx.updateStringCell(2, 1, date + "   " + dayFormatter.format(dateFromString).substring(0, 1).toUpperCase() +
                dayFormatter.format(dateFromString).substring(1));
    }

    public void insertRepNumber(String repNumber){
        SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyy");
        int year = Integer.parseInt(dayFormatter.format(dateFromString));
        int lastYear = year - 1;
        openXlsx.updateStringCell(5, 0, repNumber + "/" + lastYear + "/" + year);
    }

    public void insertDinner(String dinner1, String dinner2, String dinner3, String dinner4){
        //dzielenie tekstu, aby zmieścił się w komórkach w arkuszu
        this.dinner1 = this.dinner1+dinner1;
        this.dinner2 = this.dinner2+dinner2;
        this.dinner3 = this.dinner3+dinner3;
        this.dinner4 = this.dinner4+dinner4;
        openXlsx.updateStringCell(4, 5, this.dinner1);
        openXlsx.updateStringCell(5, 5, this.dinner2);
        openXlsx.updateStringCell(6, 5, this.dinner3);
        openXlsx.updateStringCell(7, 5, this.dinner4);
    }

    public void insertPodstawowka(String podstawowa){
    	if(!podzialPodstawowa.isEmpty()) {
    		openXlsx.updateStringCell(9, 5, podstawowa + "(" + podzialPodstawowa + ")");
    	}else{
    		openXlsx.updateStringCell(9, 5, podstawowa);
    	}
    	studentsSum += Integer.parseInt(podstawowa);
        personsSum += Integer.parseInt(podstawowa);
    }

    public void insertNauczyciele(String nauczyciele){
        openXlsx.updateStringCell(11, 5, nauczyciele);
        if(!nauczyciele.isEmpty()){
            personsSum += Integer.parseInt(nauczyciele);
        }
    }
    
    public void insertPrzedszkole(String przedszkole) {
    	if(!podzialPrzedszkole.isEmpty()) {
    		openXlsx.updateStringCell(9, 6, "Przedszkole: " + przedszkole + "(" + podzialPrzedszkole + ")");
    	}else{
    		openXlsx.updateStringCell(9, 6, przedszkole);
    	}
    	if(!przedszkole.isEmpty()) {
    		studentsSum += Integer.parseInt(przedszkole);
            personsSum += Integer.parseInt(przedszkole);
    	} 	
    }
    
    public void insertZerowka(String zerowka) {
    	if(!podzialZerowka.isEmpty()) {
    		openXlsx.updateStringCell(10, 6, zerowka + "(" + podzialZerowka + ")");
    	}else{
    		openXlsx.updateStringCell(10, 6, zerowka);
    	}
    	if(!zerowka.isEmpty()) {
    		studentsSum += Integer.parseInt(zerowka);
        	personsSum += Integer.parseInt(zerowka);
    	}
    }

    public void insertProducts(List<ProductEntity> productEntityList){
        int lp = 1;
        DecimalFormat df = new java.text.DecimalFormat("0.00");
        for(String product : products){
            openXlsx.updateIntegerCell(productRowNumber, 0, lp);
            openXlsx.updateStringCell(productRowNumber, 1, product);
            openXlsx.updateStringCell(productRowNumber, 4, productEntityList.get(lp - 1).getUnit());
            openXlsx.updateDoubleCell(productRowNumber, 5, Double.parseDouble(df.format(Double.parseDouble(unitprices.get(lp - 1))).replace(",",".")));
            openXlsx.updateDoubleCell(productRowNumber, 6, Double.parseDouble(amounts.get(lp - 1)));
            openXlsx.updateDoubleCell(productRowNumber, 7, Double.parseDouble(positionValues.get(lp - 1)));
            reportCost += Double.parseDouble(positionValues.get(lp - 1));
            openXlsx.updateStringCell(productRowNumber, 8, productEntityList.get(lp - 1).getNumber());
            lp++;
            productRowNumber++;
        }
    }

    public void insertSums(){
        openXlsx.updateIntegerCell(12, 5, personsSum);
        avgCost = reportCost / personsSum;		//TODO zrobić try z dzielenia przez 0
    }

    public List<String> getProducts() {
        return products;
    }

    public List<String> getUnitprices() {
        return unitprices;
    }

    public List<String> getAmounts() {
        return amounts;
    }
    
    public List<String> getPositionValues() {
        return positionValues;
    }

    public OpenXlsx getOpenXlsx() {
        return openXlsx;
    }

    public String getDate() {
        return date;
    }

    public Date getDateFromString() {
        return dateFromString;
    }


    public Integer getPersonsSum() {
        return personsSum;
    }

    public Double getReportCost() {
        return reportCost;
    }

    public String getRepNumber() {
        return repNumber;
    }

    public Double getAvgCost() {
        return avgCost;
    }
    
    public String getNauczyciele() {
    	return nauczyciele;
    }

	public Integer getStudentsSum() {
		return studentsSum;
	}

	public List<String> getNumbers() {
		return numbers;
	}
}
