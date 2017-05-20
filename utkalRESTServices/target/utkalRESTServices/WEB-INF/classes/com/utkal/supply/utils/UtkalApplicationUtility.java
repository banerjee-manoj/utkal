package com.utkal.supply.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtkalApplicationUtility {
	
	public static String UIDateFormat ="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static String DBDateFormat ="yyyy-MM-dd";
	public static String DBDateTimeFormat ="yyyy-MM-dd HH:MM:SS";
	
	public static String getFormattedDate(String date) throws Exception{
		
	System.out.println(UIDateFormat + "  and the date is "+ date);
	SimpleDateFormat UIdateFormat = new SimpleDateFormat(UIDateFormat);
	SimpleDateFormat DBdateFormat = new SimpleDateFormat(DBDateFormat);	
	Date newDate = UIdateFormat.parse(date);
	
	
	Calendar cal = Calendar.getInstance();
	cal.setTime(newDate);
	cal.add(Calendar.DATE, 1);
	System.out.println("**********  "+cal.getTime());
	
	
	
	System.out.println("Return date is "+ DBdateFormat.format(cal));
	
	return DBdateFormat.format(cal);
	}

}
