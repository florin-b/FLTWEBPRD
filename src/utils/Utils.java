package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import enums.EnumDateDiff;

public class Utils {

	public static String dateDiff(String dateStart, String dateStop) {

		StringBuilder result = new StringBuilder();

		if (dateStart.length() == 0 || dateStop.length() == 0)
			return result.toString();

		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh:mm:ss", new Locale("en"));

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
				if (diffMinutes != 0) {
					diffMinutes = 60 - Math.abs(diffMinutes);
					result.append(diffMinutes);
					result.append(" minute");
				}
			}

		} catch (Exception e) {
			System.out.println("dateDiff " + e.toString());
		}

		return result.toString();

	}

	public static int dateDiff(String dateStart, String dateStop, EnumDateDiff dateDiff) {

		int returnValue = -1;

		if (dateStart.length() == 0 || dateStop.length() == 0)
			return -1;

		try {

			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh:mm:ss", new Locale("en"));

			Date d1 = dateFormat.parse(dateStart);
			Date d2 = dateFormat.parse(dateStop);

			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();

			c1.setTime(d1);
			c2.setTime(d2);

			returnValue = c1.get(Calendar.DAY_OF_YEAR) - c2.get(Calendar.DAY_OF_YEAR);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return returnValue;

	}

}
