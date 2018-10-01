package com.utkal.supply.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class UtkalApplicationUtility {
	
	public static String UIDateFormat ="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static String DBDateFormat ="yyyy-MM-dd";
	public static String DBDateTimeFormat ="yyyy-MM-dd HH:MM:SS";
	public static String EMMMDDFormat="E MMM dd HH:mm:ss Z yyyy";
	public static String NICEFORMAT="dd-MMMM-yyyy";
	
	public static String getFormattedDate(String date) throws Exception{
		
	SimpleDateFormat UIdateFormat = new SimpleDateFormat(UIDateFormat);
	SimpleDateFormat DBdateFormat = new SimpleDateFormat(DBDateFormat);	
	Date newDate = UIdateFormat.parse(date);
	
	
	
	/*Calendar cal = Calendar.getInstance();
	cal.setTime(newDate);
	cal.add(Calendar.DATE, 1);
	*/
	
	DateUtils.addDays(newDate, 1);
	
	return DBdateFormat.format(DateUtils.addDays(newDate, 1));
	}

	
	
	public static String getUIFormattedDate(String date) throws Exception{
		
		
		SimpleDateFormat UIdateFormat = new SimpleDateFormat(UIDateFormat);
		SimpleDateFormat DBdateFormat = new SimpleDateFormat(DBDateFormat);	
		Date newDate = DBdateFormat.parse(date);
		return UIdateFormat.format(newDate);
		
	}
	
	/*public static  void main(String args[]) throws Exception{
	}
	*/
	
	
	
	
	public static String getFormattedDateWithoutModification(String date) throws Exception{
		
		SimpleDateFormat UIdateFormat = new SimpleDateFormat(UIDateFormat);
		SimpleDateFormat DBdateFormat = new SimpleDateFormat(DBDateFormat);	
		Date newDate = UIdateFormat.parse(date);
		
		
		
		/*Calendar cal = Calendar.getInstance();
		cal.setTime(newDate);
		cal.add(Calendar.DATE, 1);
		*/
		
		/*DateUtils.addDays(newDate, 1);
		*/
		return DBdateFormat.format(newDate);
		}

	
	
	
	
}
