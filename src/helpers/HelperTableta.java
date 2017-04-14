package helpers;

import java.util.List;

import beans.BeanSmsEmis;
import beans.BeanStareTableta;

public class HelperTableta {

	public static String formatTableteSoferi(List<BeanStareTableta> tablete) {

		StringBuilder strResult = new StringBuilder();

		strResult.append("<table class='imagetable'><tr>");
		strResult.append("<th align='center'>Cod tableta</th>");
		strResult.append("<th align='center'>Data</th>");
		strResult.append("<th align='center'>Stare</th>");

		for (BeanStareTableta tableta : tablete) {

			strResult.append("<tr align='center'>");

			strResult.append("<td>");
			strResult.append(tableta.getId());
			strResult.append("</td>");

			strResult.append("<td>");
			strResult.append(tableta.getDataInreg());
			strResult.append("</td>");

			strResult.append("<td>");
			strResult.append(tableta.getStare());
			strResult.append("</td></tr>");

		}

		strResult.append("</table>");

		return strResult.toString();
	}

}
