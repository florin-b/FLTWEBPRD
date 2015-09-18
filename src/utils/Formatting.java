package utils;

public class Formatting {

	public static String dateFormat(String date) {
		String formattedDate = "";

		if (date.contains("-")) {
			String[] dateArray = date.split("-");
			formattedDate = dateArray[2] + dateArray[1] + dateArray[0];

		}

		return formattedDate;
	}

}
