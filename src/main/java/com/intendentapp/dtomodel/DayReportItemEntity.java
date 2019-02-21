package com.intendentapp.dtomodel;

import java.io.Serializable;

import javax.persistence.*;

@Entity(name = "day_report_item")
public class DayReportItemEntity implements Serializable {

	@Id
	@Column(name = "id_day_report_item")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idDayReportItem;
	
	@Column(name = "lp")
	private Integer lp;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "unitprice")
	private Double unitprice;
	
	@Column(name = "amount")
	private Double amount;
	
	@Column(name = "value")
	private Double value;
	
	@Column(name = "number")
	private String number;
	
	@Column(name = "id_day_report")
	private Integer idDayReport;
	
	public DayReportItemEntity() {}

	public DayReportItemEntity(Integer idDayReportItem, Integer lp, String name, Double unitprice, Double amount,
			Double value, String number, Integer idDayReport) {
		super();
		this.idDayReportItem = idDayReportItem;
		this.lp = lp;
		this.name = name;
		this.unitprice = unitprice;
		this.amount = amount;
		this.value = value;
		this.number = number;
		this.idDayReport = idDayReport;
	}

	public Integer getIdDayReportItem() {
		return idDayReportItem;
	}

	public void setIdDayReportItem(Integer idDayReportItem) {
		this.idDayReportItem = idDayReportItem;
	}

	public Integer getLp() {
		return lp;
	}

	public void setLp(Integer lp) {
		this.lp = lp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getIdDayReport() {
		return idDayReport;
	}

	public void setIdDayReport(Integer idDayReport) {
		this.idDayReport = idDayReport;
	}

	
}
