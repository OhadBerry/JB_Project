package utils;

import java.util.Date;

public class DateUtils {
	
	public static Date getCurrentDateAndTime(){
		return new Date();
	}
	
	public static String getCurrentDateAndTimeString() {
		return new Date().toString();
	}

}
