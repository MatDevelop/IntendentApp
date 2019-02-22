package com.intendentapp.utils;

import com.intendentapp.model.DayReport;

public class DayReportUtils {
	
	private DayReportUtils() {}
	
	public static DayReport generateDayReportWithTestData(){
		DayReport dr = new DayReport();
		dr.setDinner1("Obiad 1");
    	dr.setDinner2("Obiad 2");
    	dr.setDinner3("Obiad 3");
    	dr.setDinner4("Obiad 4");
    	dr.setDate("2000-01-01");
    	dr.setReportNumber("1");
    	dr.setPodstawowa("10");
    	dr.setPrzedszkole("6");
    	dr.setNauczyciele("5");
    	dr.setPodzialPodstawowa("5+5");
    	dr.setPodzialPrzedszkole("3+3");
    	return dr;
	}

}
