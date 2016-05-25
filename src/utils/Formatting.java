package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.expression.ParseException;

public class Formatting {

	public static String dateFormat(String date) {
		String formattedDate = "";

		if (date.contains("-")) {
			String[] dateArray = date.split("-");
			formattedDate = dateArray[2] + dateArray[1] + dateArray[0];

		}

		return formattedDate;
	}

	public static String formatFromSap(String target) {

		String formattedDate = "";

		try {
			SimpleDateFormat formatFinal = new SimpleDateFormat("yyyyMMdd HHmmss", Locale.US);
			Date date = formatFinal.parse(target);

			String pattern = "dd-MMM-yy HH:mm:ss";
			SimpleDateFormat formatInit = new SimpleDateFormat(pattern, Locale.US);

			formattedDate = formatInit.format(date);

		} catch (java.text.ParseException ex) {
			System.out.println(ex.toString());
		}
		return formattedDate;

	}

}
