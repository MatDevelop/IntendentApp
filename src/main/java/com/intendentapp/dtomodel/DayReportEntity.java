package com.intendentapp.dtomodel;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity(name="day_report_document")
public class DayReportEntity implements Serializable {
	
	@Id
	@Column(name = "id_day_report")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idDayReport;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "report_number")
	private Integer reportNumber;
	
	@Column(name = "dinner1")
	private String dinner1;
	
	@Column(name = "dinner2")
	private String dinner2;
	
	@Column(name = "dinner3")
	private String dinner3;
	
	@Column(name = "dinner4")
	private String dinner4;
	
	@Column(name = "podstawowa")
	private Integer podstawowa;
	
	@Column(name = "przedszkole")
	private Integer przedszkole;
	
	@Column(name = "zerowka")
	private Integer zerowka;
	
	@Column(name = "nauczyciele")
	private Integer nauczyciele;
	
	@Column(name = "podzial_podstawowa")
	private String podzialPodstawowa;
	
	@Column(name = "podzial_przedszkole")
	private String podzialPrzedszkole;
	
	@Column(name = "podzial_zerowka")
	private String podzialZerowka;
	
	@Column(name = "day_report_value")
	private Double dayReportValue;
	
	@Column(name = "day_report_avg")
	private Double dayReportAvg;
	
	@OneToMany(cascade = CascadeType.ALL,
	        orphanRemoval = true)
	@JoinColumn(name = "id_day_report")
	private List<DayReportItemEntity> dayReportItems;
	
	public DayReportEntity() {}

	public DayReportEntity(Integer idDayReport, Date date, Integer reportNumber, String dinner1, String dinner2,
			String dinner3, String dinner4, Integer podstawowa, Integer przedszkole, Integer zerowka,
			Integer nauczyciele, String podzialPodstawowa, String podzialPrzedszkole, String podzialZerowka,
			Double dayReportValue, Double dayReportAvg, List<DayReportItemEntity> dayReportItems) {
		super();
		this.idDayReport = idDayReport;
		this.date = date;
		this.reportNumber = reportNumber;
		this.dinner1 = dinner1;
		this.dinner2 = dinner2;
		this.dinner3 = dinner3;
		this.dinner4 = dinner4;
		this.podstawowa = podstawowa;
		this.przedszkole = przedszkole;
		this.zerowka = zerowka;
		this.nauczyciele = nauczyciele;
		this.podzialPodstawowa = podzialPodstawowa;
		this.podzialPrzedszkole = podzialPrzedszkole;
		this.podzialZerowka = podzialZerowka;
		this.dayReportValue = dayReportValue;
		this.dayReportAvg = dayReportAvg;
		this.dayReportItems = dayReportItems;
	}

	public Integer getIdDayReport() {
		return idDayReport;
	}

	public void setIdDayReport(Integer idDayReport) {
		this.idDayReport = idDayReport;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(Integer reportNumber) {
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

	public Integer getPodstawowa() {
		return podstawowa;
	}

	public void setPodstawowa(Integer podstawowa) {
		this.podstawowa = podstawowa;
	}

	public Integer getPrzedszkole() {
		return przedszkole;
	}

	public void setPrzedszkole(Integer przedszkole) {
		this.przedszkole = przedszkole;
	}

	public Integer getZerowka() {
		return zerowka;
	}

	public void setZerowka(Integer zerowka) {
		this.zerowka = zerowka;
	}

	public Integer getNauczyciele() {
		return nauczyciele;
	}

	public void setNauczyciele(Integer nauczyciele) {
		this.nauczyciele = nauczyciele;
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

	public List<DayReportItemEntity> getDayReportItems() {
		return dayReportItems;
	}

	public void setDayReportItems(List<DayReportItemEntity> dayReportItems) {
		this.dayReportItems = dayReportItems;
	}
}