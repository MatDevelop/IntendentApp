package com.intendentapp.dtomodel;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity(name = "month_report")
public class MonthReportEntity implements Serializable{

	@Id
	@Column(name = "id_month_report")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idMonthReport;
	
	@Column(name = "for_month")
	private String forMonth;
	
	@Column(name = "month_report_quota")
	private Double monthReportQuota;
	
	@Column(name = "students_sum")
	private Integer studentsSum;
	
	@Column(name = "other_persons_sum")
	private Integer otherPersonsSum;
	
	@Column(name = "sum_persons")
	private Integer sumPersons;
	
	@Column(name = "avg_month_report_quota")
	private Double avgMonthReportQuota;
	
	@Column(name = "students_payment")
	private Double studentsPayment;
	
	@Column(name = "other_payment_for_dinner")
	private Double otherPaymentForDinner;
	
	@Column(name = "other_payment_for_costs")
	private Double otherPaymentForCosts;
	
	@Column(name = "sum_payments")
	private Double sumPayments;
	
	@Column(name = "retirement")
	private Double retirement;
	
	@OneToMany(cascade = CascadeType.ALL,
	        orphanRemoval = true)
	@JoinColumn(name = "id_month_report")
	private List<MonthReportItemEntity> monthReportItems;
	
	public MonthReportEntity() {}

	public MonthReportEntity(Integer idMonthReport, String forMonth, Double monthReportQuota, Integer studentsSum,
			Integer otherPersonsSum, Integer sumPersons, Double avgMonthReportQuota, Double studentsPayment,
			Double otherPaymentForDinner, Double otherPaymentForCosts, Double sumPayments, Double retirement,
			List<MonthReportItemEntity> monthReportItems) {
		super();
		this.idMonthReport = idMonthReport;
		this.forMonth = forMonth;
		this.monthReportQuota = monthReportQuota;
		this.studentsSum = studentsSum;
		this.otherPersonsSum = otherPersonsSum;
		this.sumPersons = sumPersons;
		this.avgMonthReportQuota = avgMonthReportQuota;
		this.studentsPayment = studentsPayment;
		this.otherPaymentForDinner = otherPaymentForDinner;
		this.otherPaymentForCosts = otherPaymentForCosts;
		this.sumPayments = sumPayments;
		this.retirement = retirement;
		this.monthReportItems = monthReportItems;
	}

	public Integer getIdMonthReport() {
		return idMonthReport;
	}

	public void setIdMonthReport(Integer idMonthReport) {
		this.idMonthReport = idMonthReport;
	}

	public String getForMonth() {
		return forMonth;
	}

	public void setForMonth(String forMonth) {
		this.forMonth = forMonth;
	}

	public Double getMonthReportQuota() {
		return monthReportQuota;
	}

	public void setMonthReportQuota(Double monthReportQuota) {
		this.monthReportQuota = monthReportQuota;
	}

	public Integer getStudentsSum() {
		return studentsSum;
	}

	public void setStudentsSum(Integer studentsSum) {
		this.studentsSum = studentsSum;
	}

	public Integer getOtherPersonsSum() {
		return otherPersonsSum;
	}

	public void setOtherPersonsSum(Integer otherPersonsSum) {
		this.otherPersonsSum = otherPersonsSum;
	}

	public Integer getSumPersons() {
		return sumPersons;
	}

	public void setSumPersons(Integer sumPersons) {
		this.sumPersons = sumPersons;
	}

	public Double getAvgMonthReportQuota() {
		return avgMonthReportQuota;
	}

	public void setAvgMonthReportQuota(Double avgMonthReportQuota) {
		this.avgMonthReportQuota = avgMonthReportQuota;
	}

	public Double getStudentsPayment() {
		return studentsPayment;
	}

	public void setStudentsPayment(Double studentsPayment) {
		this.studentsPayment = studentsPayment;
	}

	public Double getOtherPaymentForDinner() {
		return otherPaymentForDinner;
	}

	public void setOtherPaymentForDinner(Double otherPaymentForDinner) {
		this.otherPaymentForDinner = otherPaymentForDinner;
	}

	public Double getOtherPaymentForCosts() {
		return otherPaymentForCosts;
	}

	public void setOtherPaymentForCosts(Double otherPaymentForCosts) {
		this.otherPaymentForCosts = otherPaymentForCosts;
	}

	public Double getSumPayments() {
		return sumPayments;
	}

	public void setSumPayments(Double sumPayments) {
		this.sumPayments = sumPayments;
	}

	public Double getRetirement() {
		return retirement;
	}

	public void setRetirement(Double retirement) {
		this.retirement = retirement;
	}

	public List<MonthReportItemEntity> getMonthReportItems() {
		return monthReportItems;
	}

	public void setMonthReportItems(List<MonthReportItemEntity> monthReportItems) {
		this.monthReportItems = monthReportItems;
	}
}
