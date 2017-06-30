package helpers;

import java.util.List;
import beans.BeanSmsEmis;

public class HelperSMS {

	private static int sosiri30min = 0;
	private static int sosiri30_60min = 0;
	private static int sosiri60_120min = 0;
	private static int sosiri120min = 0;

	private static int bord75_100 = 0;
	private static int bord50_75 = 0;
	private static int bord25_50 = 0;
	private static int bord0_25 = 0;

	private static int nrTotalBorderouri = 0;

	public static String formatAnalizaSMS(List<BeanSmsEmis> listSms) {

		StringBuilder strResult = new StringBuilder();

		int alerteInInterval = 0;

		sosiri30min = 0;
		sosiri30_60min = 0;
		sosiri60_120min = 0;
		sosiri120min = 0;

		bord75_100 = 0;
		bord50_75 = 0;
		bord25_50 = 0;
		bord0_25 = 0;

		nrTotalBorderouri = 0;

		int cont = 1;
		try {

			strResult.append("<table class='imagetable'><tr>");
			strResult.append("<th align='center'>Nr</th>");
			strResult.append("<th align='center'>Cod sofer</th>");
			strResult.append("<th align='center'>Document</th>");
			strResult.append("<th align='center'>Client</th>");
			strResult.append("<th align='center'>Emitere sms</th>");
			strResult.append("<th align='center'>Sosire client</th>");
			strResult.append("<th align='center'>Interval (min)</th></tr>");

			String nrBord = "";

			int nrLivrCorect = 0;
			int nrTotalLivr = 0;

			for (BeanSmsEmis sms : listSms) {

				if (nrBord != "" && !nrBord.equals(sms.getDocument())) {

					double procBord = 0;
					if (nrTotalLivr > 0)
						procBord = (double) nrLivrCorect / (double) nrTotalLivr * 100;

					if (procBord >= 75 && procBord <= 100)
						bord75_100++;

					if (procBord >= 50 && procBord < 75)
						bord50_75++;

					if (procBord >= 25 && procBord < 50)
						bord25_50++;

					if (procBord >= 0 && procBord < 25)
						bord0_25++;

					nrLivrCorect = 0;
					nrTotalLivr = 0;
					nrTotalBorderouri++;

				}

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

				if (sms.getInterval() > 0 && sms.getInterval() <= 120) {
					strAlign = " align='center' ";
					alerteInInterval++;
					nrLivrCorect++;
				}

				if (sms.getInterval() > 120 && sms.getInterval() <= 150) {
					sosiri30min++;
				}

				if (sms.getInterval() > 150 && sms.getInterval() <= 180) {
					sosiri30_60min++;
				}

				if (sms.getInterval() > 180 && sms.getInterval() <= 240) {

					sosiri60_120min++;
				}

				if (sms.getInterval() > 240) {

					sosiri120min++;
				}

				strResult.append("<td " + strAlign + " >");
				strResult.append(sms.getInterval());
				strResult.append("</td></tr>");

				cont++;

				nrTotalLivr++;
				nrBord = sms.getDocument();

			}

			double procBord = 0;
			if (nrTotalLivr > 0) {
				procBord = (double) nrLivrCorect / (double) nrTotalLivr * 100;

				if (procBord >= 75 && procBord <= 100)
					bord75_100++;

				if (procBord >= 50 && procBord < 75)
					bord50_75++;

				if (procBord >= 25 && procBord < 50)
					bord25_50++;

				if (procBord >= 0 && procBord < 25)
					bord0_25++;

				nrTotalBorderouri++;
			}

			strResult.append("</table>");

		} catch (Exception ex) {
			System.out.println("displayFormattedResults : " + ex.toString());
		}
		return strResult.toString() + "#" + formatSumarSms(--cont, alerteInInterval);

	}

	public static String formatSumarSms(int totalAlerte, int inInterval) {
		StringBuilder strResult = new StringBuilder();

		strResult.append("<table class='imagetable'>");
		strResult.append("<th colspan='2' align='left'>Sumar</th>");

		strResult.append("<tr><td>");
		strResult.append("Borderouri");
		strResult.append("</td>");

		strResult.append("<td>");
		strResult.append(nrTotalBorderouri);
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("Alerte emise");
		strResult.append("</td>");

		strResult.append("<td>");
		strResult.append(totalAlerte);
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("Sosiri in intervalul 0-120 minute");
		strResult.append("</td>");

		strResult.append("<td>");
		strResult.append(inInterval);
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("Sosiri in afara intervalului");
		strResult.append("</td>");

		strResult.append("<td>");
		strResult.append(totalAlerte - inInterval);
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("Rata succes");
		strResult.append("</td>");

		strResult.append("<td>");
		double succes = 0;
		if (totalAlerte > 0)
			succes = 100 - (double) (totalAlerte - inInterval) / totalAlerte * 100;

		strResult.append(String.format("%.2f", succes) + "%");
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("Intarzieri");
		strResult.append("</td>");

		strResult.append("<td>");
		strResult.append(" ");
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("0-30 min");
		strResult.append("</td>");

		strResult.append("<td>");
		double succes30 = 0;

		if (totalAlerte > 0)
			succes30 = 100 - (double) (totalAlerte - sosiri30min) / totalAlerte * 100;
		strResult.append(String.format("%.2f", succes30) + "% (" + sosiri30min + ")");
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("30-60 min");
		strResult.append("</td>");

		strResult.append("<td>");
		double succes60 = 0;

		if (totalAlerte > 0)
			succes60 = 100 - (double) (totalAlerte - sosiri30_60min) / totalAlerte * 100;
		strResult.append(String.format("%.2f", succes60) + "% (" + sosiri30_60min + ")");
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("60-120 min");
		strResult.append("</td>");

		strResult.append("<td>");
		double succes120 = 0;

		if (totalAlerte > 0)
			succes120 = 100 - (double) (totalAlerte - sosiri60_120min) / totalAlerte * 100;
		strResult.append(String.format("%.2f", succes120) + "% (" + sosiri60_120min + ")");
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("Peste 120 min");
		strResult.append("</td>");

		strResult.append("<td>");
		double succes240 = 0;

		if (totalAlerte > 0)
			succes240 = 100 - (double) (totalAlerte - sosiri120min) / totalAlerte * 100;
		strResult.append(String.format("%.2f", succes240) + "% (" + sosiri120min + ")");
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("Procent din borderou livrat la timp");
		strResult.append("</td>");

		strResult.append("<td>");
		strResult.append(" ");
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("75% - 100%");
		strResult.append("</td>");

		strResult.append("<td>");

		double procBord = 0;
		if (nrTotalBorderouri > 0)
			procBord = (double) (bord75_100) / (double) nrTotalBorderouri * 100;

		strResult.append(String.format("%.2f", procBord) + "% (" + bord75_100 + ")");
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("50% - 75%");
		strResult.append("</td>");

		strResult.append("<td>");

		if (nrTotalBorderouri > 0)
			procBord = (double) (bord50_75) / (double) nrTotalBorderouri * 100;

		strResult.append(String.format("%.2f", procBord) + "% (" + bord50_75 + ")");
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("25% - 50%");
		strResult.append("</td>");

		strResult.append("<td>");

		if (nrTotalBorderouri > 0)
			procBord = (double) (bord25_50) / (double) nrTotalBorderouri * 100;

		strResult.append(String.format("%.2f", procBord) + "% (" + bord25_50 + ")");
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("0% - 25%");
		strResult.append("</td>");

		strResult.append("<td>");
		if (nrTotalBorderouri > 0)
			procBord = (double) (bord0_25) / (double) nrTotalBorderouri * 100;

		strResult.append(String.format("%.2f", procBord) + "% (" + bord0_25 + ")");
		strResult.append("</td></tr>");

		strResult.append("</table>");

		return strResult.toString();
	}

}
