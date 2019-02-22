package com.intendentapp.insert;

import java.util.List;

import com.intendentapp.configuration.OpenXlsx;
import com.intendentapp.model.Card;
import com.intendentapp.utils.Utils;

public class InsertToCardExcelReport {
	
	private int startRowNumber;	//wiersz początkowy do wprowadzania danych
	private String name;
	private String number;
	private String shopName;
	private String unit;
	private List<String> rowNumbers;	//numery wierszy
	private List<String> przychodPurchaseDates;
	private List<String> przychodUnitprices;
	private List<String> przychodAmounts;
	private List<String> przychodValues;
	private List<String> rozchodDatesOut;
	private List<String> rozchodUnitprices;
	private List<String> rozchodAmounts;
	private List<String> rozchodValues;
	private List<String> rozchodStates;
	private List<String> rozchodRemainedValues;
	private List<String> rozchodReportNumbers;
	private OpenXlsx openXlsx;      //instancja pliku excel
	
	public InsertToCardExcelReport() {}
	
	public InsertToCardExcelReport(Card card, String filename) {
		//TODO ustawic początkowy wiersz startRowNumber = ;
		//TODO nazwa numer + nazwa karty, przy tworzeniu nowej karty dopisywac do pliku #ARCHIVED i datę i do nazwy w bazie tez archived i datę
		openXlsx = new OpenXlsx(filename, "src/main/webapp/static/cards/" + card.getNumber()+card.getName()+".xlsx");     //utworzenie obiektu pliku excelowego	//TODO utwrzyc folder cards
        name = card.getName();
        number = card.getNumber();
        shopName = card.getShopName();
        unit = card.getUnit();
        rowNumbers = Utils.splitAndReturnList(card.getRowNumbers());
        przychodPurchaseDates =  Utils.splitAndReturnList(card.getPrzychodPurchaseDates());
        przychodUnitprices = Utils.splitAndReturnList(card.getPrzychodUnitprices());
        przychodAmounts = Utils.splitAndReturnList(card.getPrzychodAmounts());
        przychodValues = Utils.splitAndReturnList(card.getPrzychodValues());
        rozchodDatesOut = Utils.splitAndReturnList(card.getRozchodDatesOut());
        rozchodUnitprices = Utils.splitAndReturnList(card.getRozchodUnitprices());
        rozchodAmounts = Utils.splitAndReturnList(card.getRozchodAmounts());
        rozchodValues = Utils.splitAndReturnList(card.getRozchodValues());
        rozchodStates = Utils.splitAndReturnList(card.getRozchodStates());
        rozchodRemainedValues = Utils.splitAndReturnList(card.getRozchodRemainedValues());
        rozchodReportNumbers = Utils.splitAndReturnList(card.getRozchodReportNumbers());
        
	}
	
	public void insertAll() {
		/*openXlsx.updateStringCell(rowNumber, cellNumber, name);
		openXlsx.updateStringCell(rowNumber, cellNumber, number);
		openXlsx.updateStringCell(rowNumber, cellNumber, shopName);
		openXlsx.updateStringCell(rowNumber, cellNumber, unit);*/
		int lp=0;
		for(String row : rowNumbers) {
			if(Utils.isEmptyRow(przychodPurchaseDates.get(lp), rozchodDatesOut.get(lp))) {
				break;
			}
			/*openXlsx.updateStringCell(rowNumber, cellNumber, przychodPurchaseDates.get(lp));
			openXlsx.updateDoubleCell(rowNumber, cellNumber, przychodUnitprices.get(lp));
			openXlsx.updateDoubleCell(rowNumber, cellNumber, przychodAmounts.get(lp));
			openXlsx.updateDoubleCell(rowNumber, cellNumber, przychodValues.get(lp));
			openXlsx.updateStringCell(rowNumber, cellNumber, rozchodDatesOut.get(lp));
			openXlsx.updateDoubleCell(rowNumber, cellNumber, rozchodUnitprices.get(lp));
			openXlsx.updateDoubleCell(rowNumber, cellNumber, rozchodAmounts.get(lp));
			openXlsx.updateDoubleCell(rowNumber, cellNumber, rozchodValues.get(lp));
			openXlsx.updateDoubleCell(rowNumber, cellNumber, rozchodStates.get(lp));
			openXlsx.updateDoubleCell(rowNumber, cellNumber, rozchodRemainedValues.get(lp));
			openXlsx.updateIntegerCell(rowNumber, cellNumber, rozchodReportNumbers.get(lp));*/
			
		}
	}
	
	
	public OpenXlsx getOpenXlsx() {
		return openXlsx;
	}

	public int getStartRowNumber() {
		return startRowNumber;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getShopName() {
		return shopName;
	}

	public String getUnit() {
		return unit;
	}

	public List<String> getRowNumbers() {
		return rowNumbers;
	}

	public List<String> getPrzychodPurchaseDates() {
		return przychodPurchaseDates;
	}

	public List<String> getPrzychodUnitprices() {
		return przychodUnitprices;
	}

	public List<String> getPrzychodAmounts() {
		return przychodAmounts;
	}

	public List<String> getPrzychodValues() {
		return przychodValues;
	}

	public List<String> getRozchodDatesOut() {
		return rozchodDatesOut;
	}

	public List<String> getRozchodUnitprices() {
		return rozchodUnitprices;
	}

	public List<String> getRozchodAmounts() {
		return rozchodAmounts;
	}

	public List<String> getRozchodValues() {
		return rozchodValues;
	}

	public List<String> getRozchodStates() {
		return rozchodStates;
	}

	public List<String> getRozchodRemainedValues() {
		return rozchodRemainedValues;
	}

	public List<String> getRozchodReportNumbers() {
		return rozchodReportNumbers;
	}

}
