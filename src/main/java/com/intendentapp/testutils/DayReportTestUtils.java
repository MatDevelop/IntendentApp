package com.intendentapp.testutils;

import com.intendentapp.model.DayReport;

public class DayReportTestUtils {
	
	private DayReportTestUtils() {}
	
	public static DayReport createTestDayReport() {
		DayReport dayReport = new DayReport();
		dayReport.setDinner1("Obiad 1");
    	dayReport.setDinner2("Obiad 2");
    	dayReport.setDinner3("Obiad 3");
    	dayReport.setDinner4("Obiad 4");
    	dayReport.setDate("2000-01-01");
    	dayReport.setReportNumber("1");
    	dayReport.setPodstawowa("10");
    	dayReport.setPrzedszkole("6");
    	dayReport.setNauczyciele("5");
    	dayReport.setZerowka("");
    	dayReport.setPodzialPodstawowa("5+5");
    	dayReport.setPodzialPrzedszkole("3+3");
    	dayReport.setPodzialZerowka("");
    	dayReport.setProduct("Cebula,...,...");
    	dayReport.setAmount("2");
    	dayReport.setUnitprice("4.34");
    	dayReport.setPositionValue("8.62");
    	dayReport.setNumber("1s");
    	return dayReport;
	}
}
