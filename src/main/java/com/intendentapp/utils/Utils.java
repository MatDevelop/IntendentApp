package com.intendentapp.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Utils {
	
	public static List<String> splitAndReturnList(String s){
		return new LinkedList<String>(Arrays.asList(s.split(",")));
	}
	
	public static boolean isEmptyRow(String przychodPurchaseDates, String rozchodDatesOut) {
		if(przychodPurchaseDates.isEmpty() && rozchodDatesOut.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static Date convertStringToDate(String dateInString) {
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 try {
			 return formatter.parse(dateInString);
		 }catch (ParseException e) {
			System.out.println("Błąd przy parsowaniu String na Date.");
			e.printStackTrace();
		 }
		 return new Date();
	}
	
	public static Double convertStringToDouble(String doubleInString) {
		DecimalFormat df=new java.text.DecimalFormat("0.00");
		try {
			return Double.parseDouble(df.format(Double.parseDouble(doubleInString)).replace(",","."));
		}catch (NumberFormatException e) {
			System.out.println("Błąd przy parsowaniu String na Double");
		}
		return new Double("0.00");
	}
	
	public static Integer convertStringToInteger(String integerInString) {
		try {
			return Integer.parseInt(integerInString);
		} catch (NumberFormatException e) {
			System.out.println("Błąd przy parsowaniu String na Integer");
		}
		return 0;
	}
	
}