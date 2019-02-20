package com.Automation.project.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeHelper 
{
	public static DateFormat format;
	public static Calendar calendar;
	public static String time;
	
	public static String getCurrentDateTime()
	{
		format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		calendar = Calendar.getInstance();
		time = ""+ format.format(calendar.getTime());
		return time;
	}
	
	public static String getCurrentDate()
	{
		return getCurrentDateTime().substring(0,11);	
	}

}
