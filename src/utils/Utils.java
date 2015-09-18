package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static String dateDiff(String dateStart, String dateStop) {

		StringBuilder result = new StringBuilder();

		if (dateStart.length() == 0 || dateStop.length() == 0)
			return result.toString();

		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");

			Date d1 = dateFormat.parse(dateStart);
			Date d2 = dateFormat.parse(dateStop);

			long diff = d2.getTime() - d1.getTime();

			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			if (diffDays > 0) {
				result.append(diffDays);
				result.append(" zile ");
			}

			if (diffHours > 0) {
				result.append(diffHours);
				result.append(" ore ");
			}

			if (diffMinutes > 0) {
				result.append(diffMinutes);
				result.append(" minute");
			} else {
				diffMinutes = 60 - Math.abs(diffMinutes);
				result.append(diffMinutes);
				result.append(" minute");
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result.toString();

	}

}
