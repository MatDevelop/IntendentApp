package com.intendentapp.model;

public class DayReport {
    
	private String date;
    private String reportNumber;
    private String dinner1;
    private String dinner2;
    private String dinner3;
    private String dinner4;
    private String podstawowa;
    private String zerowka;
    private String przedszkole;
    private String nauczyciele;
    private String product;
    private String unitprice;
    private String amount;
    private String positionValue;
    private String podzialPodstawowa;
    private String podzialPrzedszkole;
    private String podzialZerowka;
    private Double dayReportValue;
    private Double dayReportAvg;
    private String number;

    public DayReport(){}

	public DayReport(String date, String reportNumber, String dinner1, String dinner2, String dinner3, String dinner4,
			String podstawowa, String zerowka, String przedszkole, String nauczyciele, String product, String unitprice,
			String amount, String positionValue, String podzialPodstawowa, String podzialPrzedszkole,
			String podzialZerowka, Double dayReportValue, Double dayReportAvg, String number) {
		super();
		this.date = date;
		this.reportNumber = reportNumber;
		this.dinner1 = dinner1;
		this.dinner2 = dinner2;
		this.dinner3 = dinner3;
		this.dinner4 = dinner4;
		this.podstawowa = podstawowa;
		this.zerowka = zerowka;
		this.przedszkole = przedszkole;
		this.nauczyciele = nauczyciele;
		this.product = product;
		this.unitprice = unitprice;
		this.amount = amount;
		this.positionValue = positionValue;
		this.podzialPodstawowa = podzialPodstawowa;
		this.podzialPrzedszkole = podzialPrzedszkole;
		this.podzialZerowka = podzialZerowka;
		this.dayReportValue = dayReportValue;
		this.dayReportAvg = dayReportAvg;
		this.number = number;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getDinner1() {
		return dinner1;
	}

	public void setDinner1(String dinner1) {
		this.dinner1 = dinner1;
	}

	public String getDinner2() {
		return dinner2;
	}

	public void setDinner2(String dinner2) {
		this.dinner2 = dinner2;
	}

	public String getDinner3() {
		return dinner3;
	}

	public void setDinner3(String dinner3) {
		this.dinner3 = dinner3;
	}

	public String getDinner4() {
		return dinner4;
	}

	public void setDinner4(String dinner4) {
		this.dinner4 = dinner4;
	}

	public String getPodstawowa() {
		return podstawowa;
	}

	public void setPodstawowa(String podstawowa) {
		this.podstawowa = podstawowa;
	}

	public String getZerowka() {
		return zerowka;
	}

	public void setZerowka(String zerowka) {
		this.zerowka = zerowka;
	}

	public String getPrzedszkole() {
		return przedszkole;
	}

	public void setPrzedszkole(String przedszkole) {
		this.przedszkole = przedszkole;
	}

	public String getNauczyciele() {
		return nauczyciele;
	}

	public void setNauczyciele(String nauczyciele) {
		this.nauczyciele = nauczyciele;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPositionValue() {
		return positionValue;
	}

	public void setPositionValue(String positionValue) {
		this.positionValue = positionValue;
	}

	public String getPodzialPodstawowa() {
		return podzialPodstawowa;
	}

	public void setPodzialPodstawowa(String podzialPodstawowa) {
		this.podzialPodstawowa = podzialPodstawowa;
	}

	public String getPodzialPrzedszkole() {
		return podzialPrzedszkole;
	}

	public void setPodzialPrzedszkole(String podzialPrzedszkole) {
		this.podzialPrzedszkole = podzialPrzedszkole;
	}

	public String getPodzialZerowka() {
		return podzialZerowka;
	}

	public void setPodzialZerowka(String podzialZerowka) {
		this.podzialZerowka = podzialZerowka;
	}

	public Double getDayReportValue() {
		return dayReportValue;
	}

	public void setDayReportValue(Double dayReportValue) {
		this.dayReportValue = dayReportValue;
	}

	public Double getDayReportAvg() {
		return dayReportAvg;
	}

	public void setDayReportAvg(Double dayReportAvg) {
		this.dayReportAvg = dayReportAvg;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}  
}