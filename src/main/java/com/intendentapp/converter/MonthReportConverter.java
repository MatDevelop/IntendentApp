package com.intendentapp.converter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.intendentapp.dtomodel.DayReportEntity;
import com.intendentapp.dtomodel.MonthReportEntity;
import com.intendentapp.dtomodel.MonthReportItemEntity;
import com.intendentapp.generator.GenerateMonthReport;

public class MonthReportConverter {

	private final Logger log = LogManager.getLogger(MonthReportConverter.class);
	
	private Double monthReportQuota;		//kwota raportu miesięcznego
	private Integer studentsSum;			//liczba uczniów
	private Integer otherPersonsSum;		//liczba innych osób
	private Integer sumPersons;				//suma wszystkich osób/posiłków	
	private Double avgMonthReportQuota;		//średni koszt za obiad
	private Double studentsPayment;			//wpłaty uczniów
	private Double otherPaymentForDinner;	//wpłaty innych osób za posiłek
	private Double otherPaymentForCosts;	//wpłaty innych osób za koszty
	private Double sumPayments;				//suma wpłat
	private Double retirement;				//rozchód z raportów
	private Double remainedValue;			//pozostałość
	
	private final Double QUOTA_PER_DINNER = 2.5d;		//kwota z obiad
	private final Double OTHERS_COST_PER_DINNER = 3.5d;	//kwota kosztów
	
	public MonthReportConverter() {
		monthReportQuota = 0.0d;
		studentsSum = 0;
		otherPersonsSum = 0;
		sumPersons = 0;
		avgMonthReportQuota = 0.0d;
		studentsPayment = 0.0d;
		otherPaymentForDinner = 0.0d;
		otherPaymentForCosts = 0.0d;
		sumPayments = 0.0d;
		retirement = 0.0d;
		remainedValue = 0.0d;
	}	
	
	public MonthReportEntity convert(MonthReportEntity monthReportEntity, DayReportEntity dayReportEntity, GenerateMonthReport generateMonthReport,
			List<MonthReportItemEntity> monthReportItemEntityList, MonthReportItemEntity monthReportItemEntity) {
		
		DecimalFormat df = new java.text.DecimalFormat("0.00");
		MonthReportItemEntity newMonthReportItemEntity = new MonthReportItemEntity();
		if(monthReportItemEntity != null) {
			newMonthReportItemEntity.setIdMonthReportItem(monthReportItemEntity.getIdMonthReportItem());
		}
		
		newMonthReportItemEntity.setDate(dayReportEntity.getDate());
		newMonthReportItemEntity.setReportNumber(dayReportEntity.getReportNumber());
		newMonthReportItemEntity.setStudents(generateMonthReport.getInsertDayReport().getStudentsSum());
		newMonthReportItemEntity.setOtherPersons(dayReportEntity.getNauczyciele());
		newMonthReportItemEntity.setSumPersons(generateMonthReport.getInsertDayReport().getPersonsSum());
		newMonthReportItemEntity.setReportQuota(dayReportEntity.getDayReportValue());
		newMonthReportItemEntity.setAvgDayReportCost(dayReportEntity.getDayReportAvg());
		monthReportItemEntityList.add(newMonthReportItemEntity);
		
		//sumujemy wartości z wszystkich raportów
		//TODO usunąć wszystkie pozycje z bazy i zapisać ponownie. Utworzyć nową listę i do niej wrzucić starą listę bez id-ków
		for(MonthReportItemEntity mrie : monthReportItemEntityList) {
			studentsSum += mrie.getStudents();
			otherPersonsSum += mrie.getOtherPersons();
			sumPersons += mrie.getSumPersons();
			monthReportQuota += mrie.getReportQuota();
		}
		
		studentsPayment = studentsSum * QUOTA_PER_DINNER;
		otherPaymentForDinner = otherPersonsSum * QUOTA_PER_DINNER;
		otherPaymentForCosts = otherPersonsSum * OTHERS_COST_PER_DINNER;
		sumPayments = studentsPayment + otherPaymentForDinner;
		avgMonthReportQuota = monthReportQuota / sumPersons;
		retirement = monthReportQuota;
		remainedValue = (avgMonthReportQuota - QUOTA_PER_DINNER) * sumPersons;
		
		monthReportEntity.setForMonth(generateMonthReport.getMonth() + generateMonthReport.getYear());
		monthReportEntity.setMonthReportQuota(Double.parseDouble(df.format(monthReportQuota).replace(",",".")));
		monthReportEntity.setOtherPaymentForCosts(Double.parseDouble(df.format(otherPaymentForCosts).replace(",",".")));
		monthReportEntity.setOtherPaymentForDinner(Double.parseDouble(df.format(otherPaymentForDinner).replace(",",".")));
		monthReportEntity.setOtherPersonsSum(otherPersonsSum);
		monthReportEntity.setRetirement(Double.parseDouble(df.format(retirement).replace(",",".")));
		monthReportEntity.setStudentsPayment(Double.parseDouble(df.format(studentsPayment).replace(",",".")));
		monthReportEntity.setStudentsSum(studentsSum);
		monthReportEntity.setSumPayments(Double.parseDouble(df.format(sumPayments).replace(",",".")));
		monthReportEntity.setAvgMonthReportQuota(Double.parseDouble(df.format(avgMonthReportQuota).replace(",",".")));
		monthReportEntity.setMonthReportItems(monthReportItemEntityList);
		monthReportEntity.setRemainedValue(Double.parseDouble(df.format(remainedValue).replace(",",".")));
		
		return monthReportEntity;
	}
}
