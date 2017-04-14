package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(new Date());
	}

	public static String getCurrentTime() {
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
		return dateFormat.format(new Date());
	}
}
