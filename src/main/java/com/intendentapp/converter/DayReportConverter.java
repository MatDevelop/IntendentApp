package com.intendentapp.converter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.intendentapp.dtomodel.DayReportEntity;
import com.intendentapp.dtomodel.DayReportItemEntity;
import com.intendentapp.generator.GenerateDayReport;
import com.intendentapp.model.DayReport;

public class DayReportConverter {
	
	private static final Logger log = LogManager.getLogger(DayReportConverter.class);
	
	private DayReportConverter() {}
	
	public static DayReportEntity convert(DayReport dayReport, DayReportEntity dayReportEntity, GenerateDayReport generateDayReport) {
    	dayReportEntity.setDate(generateDayReport.getInsert().getDateFromString());
    	dayReportEntity.setReportNumber(Integer.parseInt(dayReport.getReportNumber()));
    	dayReportEntity.setDinner1(dayReport.getDinner1());
    	dayReportEntity.setDinner2(dayReport.getDinner2());
    	dayReportEntity.setDinner3(dayReport.getDinner3());
    	dayReportEntity.setDinner4(dayReport.getDinner4());
    	
    	String podstawowa = dayReport.getPodstawowa();
    	if(!podstawowa.isEmpty()) {
    		dayReportEntity.setPodstawowa(Integer.parseInt(podstawowa));
    	}
    	
    	String przedszkole = dayReport.getPrzedszkole();
    	if(!przedszkole.isEmpty()) {
    		dayReportEntity.setPrzedszkole(Integer.parseInt(przedszkole));
    	}
    	
    	String zerowka = dayReport.getZerowka();
    	if(!zerowka.isEmpty()) {
    		dayReportEntity.setZerowka(Integer.parseInt(zerowka));
    	}
    	
    	String nauczyciele = dayReport.getNauczyciele();
    	if(!nauczyciele.isEmpty()) {
    		dayReportEntity.setNauczyciele(Integer.parseInt(nauczyciele));
    	}
    	
    	dayReportEntity.setPodzialPodstawowa(dayReport.getPodzialPodstawowa());
    	dayReportEntity.setPodzialPrzedszkole(dayReport.getPodzialPrzedszkole());
    	dayReportEntity.setPodzialZerowka(dayReport.getPodzialZerowka());
    	dayReportEntity.setDayReportValue(dayReport.getDayReportValue());
    	dayReportEntity.setDayReportAvg(dayReport.getDayReportAvg());
    	   	
    	DecimalFormat df=new java.text.DecimalFormat("0.00");
    	List<DayReportItemEntity> dayReportItems = new ArrayList<>();
    	int lp=1;
    	
    	for(int i=0; i < generateDayReport.getInsert().getProducts().size(); i++) {
    		DayReportItemEntity dayReportItem = new DayReportItemEntity();
        	dayReportItem.setLp(lp);
        	dayReportItem.setName(generateDayReport.getInsert().getProducts().get(i));
        	dayReportItem.setUnitprice(Double.parseDouble(df.format(Double.parseDouble(generateDayReport.getInsert().getUnitprices().get(i))).replace(",",".")));
        	dayReportItem.setAmount(Double.parseDouble(df.format(Double.parseDouble(generateDayReport.getInsert().getAmounts().get(i))).replace(",",".")));
        	dayReportItem.setValue(Double.parseDouble(df.format(Double.parseDouble(generateDayReport.getInsert().getPositionValues().get(i))).replace(",",".")));
        	dayReportItem.setNumber(generateDayReport.getInsert().getNumbers().get(i));
        	dayReportItems.add(dayReportItem);
        	lp++;
    	}
    	
    	dayReportEntity.setDayReportItems(dayReportItems);
    	
    	return dayReportEntity;
	}
}
