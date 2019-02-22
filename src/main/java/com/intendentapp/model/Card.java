package com.intendentapp.model;

public class Card {
	
	private Integer cardId;
	private String name;
	private String number;
	private String shopName;
	private String unit;
	private String rowNumbers;	//numery wierszy
	private String przychodPurchaseDates;
	private String przychodUnitprices;
	private String przychodAmounts;
	private String przychodValues;
	private String rozchodDatesOut;
	private String rozchodUnitprices;
	private String rozchodAmounts;
	private String rozchodValues;
	private String rozchodStates;
	private String rozchodRemainedValues;
	private String rozchodReportNumbers;
	
	public Card(Integer cardId, String name, String number, String shopName, String unit, String rowNumbers,
			String przychodPurchaseDates, String przychodUnitprices, String przychodAmounts, String przychodValues,
			String rozchodDatesOut, String rozchodUnitprices, String rozchodAmounts, String rozchodValues,
			String rozchodStates, String rozchodRemainedValues, String rozchodReportNumbers) {
		super();
		this.cardId = cardId;
		this.name = name;
		this.number = number;
		this.shopName = shopName;
		this.unit = unit;
		this.rowNumbers = rowNumbers;
		this.przychodPurchaseDates = przychodPurchaseDates;
		this.przychodUnitprices = przychodUnitprices;
		this.przychodAmounts = przychodAmounts;
		this.przychodValues = przychodValues;
		this.rozchodDatesOut = rozchodDatesOut;
		this.rozchodUnitprices = rozchodUnitprices;
		this.rozchodAmounts = rozchodAmounts;
		this.rozchodValues = rozchodValues;
		this.rozchodStates = rozchodStates;
		this.rozchodRemainedValues = rozchodRemainedValues;
		this.rozchodReportNumbers = rozchodReportNumbers;
	}
	
	public Integer getCardId() {
		return cardId;
	}
	
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getShopName() {
		return shopName;
	}
	
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public String getUnit() {
		return unit;
	}
	
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getRowNumbers() {
		return rowNumbers;
	}
	
	public void setRowNumbers(String rowNumbers) {
		this.rowNumbers = rowNumbers;
	}
	
	public String getPrzychodPurchaseDates() {
		return przychodPurchaseDates;
	}
	
	public void setPrzychodPurchaseDates(String przychodPurchaseDates) {
		this.przychodPurchaseDates = przychodPurchaseDates;
	}
	
	public String getPrzychodUnitprices() {
		return przychodUnitprices;
	}
	
	public void setPrzychodUnitprices(String przychodUnitprices) {
		this.przychodUnitprices = przychodUnitprices;
	}
	
	public String getPrzychodAmounts() {
		return przychodAmounts;
	}
	
	public void setPrzychodAmounts(String przychodAmounts) {
		this.przychodAmounts = przychodAmounts;
	}
	
	public String getPrzychodValues() {
		return przychodValues;
	}
	
	public void setPrzychodValues(String przychodValues) {
		this.przychodValues = przychodValues;
	}
	
	public String getRozchodDatesOut() {
		return rozchodDatesOut;
	}
	
	public void setRozchodDatesOut(String rozchodDatesOut) {
		this.rozchodDatesOut = rozchodDatesOut;
	}
	
	public String getRozchodUnitprices() {
		return rozchodUnitprices;
	}
	
	public void setRozchodUnitprices(String rozchodUnitprices) {
		this.rozchodUnitprices = rozchodUnitprices;
	}
	
	public String getRozchodAmounts() {
		return rozchodAmounts;
	}
	
	public void setRozchodAmounts(String rozchodAmounts) {
		this.rozchodAmounts = rozchodAmounts;
	}
	
	public String getRozchodValues() {
		return rozchodValues;
	}
	
	public void setRozchodValues(String rozchodValues) {
		this.rozchodValues = rozchodValues;
	}
	
	public String getRozchodStates() {
		return rozchodStates;
	}
	
	public void setRozchodStates(String rozchodStates) {
		this.rozchodStates = rozchodStates;
	}
	
	public String getRozchodRemainedValues() {
		return rozchodRemainedValues;
	}
	
	public void setRozchodRemainedValues(String rozchodRemainedValues) {
		this.rozchodRemainedValues = rozchodRemainedValues;
	}
	
	public String getRozchodReportNumbers() {
		return rozchodReportNumbers;
	}
	
	public void setRozchodReportNumbers(String rozchodReportNumbers) {
		this.rozchodReportNumbers = rozchodReportNumbers;
	}
}
