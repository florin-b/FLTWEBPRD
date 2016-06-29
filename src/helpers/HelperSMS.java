package helpers;

import java.util.List;
import beans.BeanSmsEmis;
import utils.Formatting;

public class HelperSMS {

	public static String formatAnalizaSMS(List<BeanSmsEmis> listSms) {

		StringBuilder strResult = new StringBuilder();

		try {

			strResult.append("<table class='imagetable'><tr>");
			strResult.append("<th align='center'>Nr</th>");
			strResult.append("<th align='center'>Cod sofer</th>");
			strResult.append("<th align='center'>Document</th>");
			strResult.append("<th align='center'>Client</th>");
			strResult.append("<th align='center'>Emitere sms</th>");
			strResult.append("<th align='center'>Sosire client</th>");
			strResult.append("<th align='center'>Interval (min)</th></tr>");

			int cont = 1;
			for (BeanSmsEmis sms : listSms) {

				strResult.append("<tr align='center'><td >");
				strResult.append(String.valueOf(cont) + ".");
				strResult.append("</td>");

				strResult.append("<td>");
				strResult.append(sms.getCodSofer());
				strResult.append("</td>");

				strResult.append("<td>");
				strResult.append(sms.getDocument());
				strResult.append("</td>");

				strResult.append("<td>");
				strResult.append(sms.getClient());
				strResult.append("</td>");

				strResult.append("<td>");
				strResult.append(sms.getDataEmitereSms());
				strResult.append("</td>");

				strResult.append("<td>");
				strResult.append(sms.getDataSosireClient());
				strResult.append("</td>");

				String strAlign = " align='right' ";

				if (sms.getInterval() > 0 && sms.getInterval() < 120)
					strAlign = " align='center' ";

				strResult.append("<td " + strAlign + " >");
				strResult.append(sms.getInterval());
				strResult.append("</td></tr>");

				cont++;

			}

			strResult.append("</table>");

		} catch (Exception ex) {
			System.out.println("displayFormattedResults : " + ex.toString());
		}
		return strResult.toString();

	}

}
