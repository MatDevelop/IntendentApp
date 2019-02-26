package com.intendentapp.dtomodel;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name = "month_report_item")
public class MonthReportItemEntity implements Serializable{

	@Id
	@Column(name = "id_month_report_item")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idMonthReportItem;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "report_number")
	private Integer reportNumber;
	
	@Column(name = "report_quota")
	private Double reportQuota;
	
	@Column(name = "students")
	private Integer students;
	
	@Column(name = "other_persons")
	private Integer otherPersons;
	
	@Column(name = "sum_persons")
	private Integer sumPersons;
	
	@Column(name = "avg_day_report_cost")
	private Double avgDayReportCost;
	
	@Column(name = "id_month_report")
	private Integer idMonthReport;
	
	public MonthReportItemEntity() {}

	public MonthReportItemEntity(Integer idMonthReportItem, Date date, Integer reportNumber, Double reportQuota,
			Integer students, Integer otherPersons, Integer sumPersons, Double avgDayReportCost,
			Integer idMonthReport) {
		super();
		this.idMonthReportItem = idMonthReportItem;
		this.date = date;
		this.reportNumber = reportNumber;
		this.reportQuota = reportQuota;
		this.students = students;
		this.otherPersons = otherPersons;
		this.sumPersons = sumPersons;
		this.avgDayReportCost = avgDayReportCost;
		this.idMonthReport = idMonthReport;
	}

	public Integer getIdMonthReportItem() {
		return idMonthReportItem;
	}

	public void setIdMonthReportItem(Integer idMonthReportItem) {
		this.idMonthReportItem = idMonthReportItem;
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

	public Double getReportQuota() {
		return reportQuota;
	}

	public void setReportQuota(Double reportQuota) {
		this.reportQuota = reportQuota;
	}

	public Integer getStudents() {
		return students;
	}

	public void setStudents(Integer students) {
		this.students = students;
	}

	public Integer getOtherPersons() {
		return otherPersons;
	}

	public void setOtherPersons(Integer otherPersons) {
		this.otherPersons = otherPersons;
	}

	public Integer getSumPersons() {
		return sumPersons;
	}

	public void setSumPersons(Integer sumPersons) {
		this.sumPersons = sumPersons;
	}

	public Double getAvgDayReportCost() {
		return avgDayReportCost;
	}

	public void setAvgDayReportCost(Double avgDayReportCost) {
		this.avgDayReportCost = avgDayReportCost;
	}

	public Integer getIdMonthReport() {
		return idMonthReport;
	}

	public void setIdMonthReport(Integer idMonthReport) {
		this.idMonthReport = idMonthReport;
	}
}
