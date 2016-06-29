package helpers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import beans.Client;
import beans.GpsInactiv;
import beans.RezultatTraseu;
import beans.SumarTraseu;
import utils.Utils;
import utils.UtilsAdrese;

public class HelperEvenimente {

	public static String formatEvenimente(Set<RezultatTraseu> rezultat) {

		StringBuilder strResult = new StringBuilder();

		try {

			strResult.append("<table class='imagetable'><tr>");
			strResult.append("<th>Nr</th>");
			strResult.append("<th>Client</th>");
			strResult.append("<th>Cod</th>");
			strResult.append("<th>Sosire</th>");
			strResult.append("<th>Plecare</th>");
			strResult.append("<th>Stationare</th></tr>");

			int cont = 1;
			for (RezultatTraseu traseu : rezultat) {

				strResult.append("<tr><td align='center'>");
				strResult.append(String.valueOf(cont) + ".");
				strResult.append("</td>");

				strResult.append("<td>");
				strResult.append(traseu.getClient().getNumeClient() + "<br>");
				strResult.append(getFormattedAddress(traseu.getClient()));
				strResult.append("</td>");

				strResult.append("<td>");
				strResult.append(traseu.getClient().getCodClient());
				strResult.append("</td>");

				strResult.append("<td>");
				strResult.append(validareSosire(traseu));
				strResult.append("</td>");

				strResult.append("<td>");
				strResult.append(validarePlecare(traseu));
				strResult.append("</td>");

				strResult.append("<td>");
				strResult.append(Utils.dateDiff(validareSosire(traseu), validarePlecare(traseu)));
				strResult.append("</td></tr>");

				cont++;

			}

			strResult.append("</table>");

		} catch (Exception ex) {
			System.out.println("displayFormattedResults : " + rezultat + " : " + Arrays.toString(ex.getStackTrace()));
		}
		return strResult.toString();

	}

	public static String formatSumar(SumarTraseu sumar) {
		StringBuilder strResult = new StringBuilder();

		strResult.append("<table class='imagetable'>");
		strResult.append("<th colspan='2' align='left'>Sumar</th>");

		strResult.append("<tr><td>");
		strResult.append("Start cursa");
		strResult.append("</td>");

		strResult.append("<td>");
		strResult.append(sumar.getDataStart() == null ? "" : sumar.getDataStart());
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("Stop cursa");
		strResult.append("</td>");

		strResult.append("<td>");
		strResult.append(sumar.getDataStop() == null ? "" : sumar.getDataStop());
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("Durata");
		strResult.append("</td>");

		strResult.append("<td>");
		strResult.append(sumar.getDurata() == null ? "" : sumar.getDurata());
		strResult.append("</td></tr>");

		strResult.append("<tr><td>");
		strResult.append("Km");
		strResult.append("</td>");

		strResult.append("<td>");
		strResult.append(sumar.getKm() == null ? "" : sumar.getKm());
		strResult.append("</td></tr>");

		strResult.append("</table>");

		return strResult.toString();
	}

	public static String formatGpsInactiv(List<GpsInactiv> listGps) {

		StringBuilder strResult = new StringBuilder();

		strResult.append("<table class='imagetable'><tr>");
		strResult.append("<th>Nr</th>");
		strResult.append("<th>Numar auto</th>");
		strResult.append("<th>Zile inactivitate</th>");

		int cont = 1;
		for (GpsInactiv gpsInactiv : listGps) {
			strResult.append("<tr><td align='center'>");
			strResult.append(String.valueOf(cont) + ".");
			strResult.append("</td>");

			strResult.append("<td align='center'>");
			strResult.append(gpsInactiv.getNrAuto());
			strResult.append("</td>");

			strResult.append("<td align='center'>");
			strResult.append(gpsInactiv.getNrZileInact());
			strResult.append("</td>");

			cont++;

		}

		return strResult.toString();

	}

	private static String validareSosire(RezultatTraseu traseu) {
		return traseu.getSosire() == null ? "" : traseu.getSosire().getData();

	}

	private static String validarePlecare(RezultatTraseu traseu) {
		if (traseu.getPlecare() == null)
			return "";
		else
			return traseu.getPlecare().getData();

	}

	private static String getFormattedAddress(Client client) {
		StringBuilder str = new StringBuilder();

		if (client.getCodJudet() != "") {
			str.append(UtilsAdrese.getNumeJudet(client.getCodJudet()));
		}

		if (client.getLocalitate() != "") {
			str.append(", ");
			str.append(client.getLocalitate());
		}

		if (client.getStrada() != "") {
			str.append(", ");
			str.append(client.getStrada());
		}

		if (client.getNrStrada().trim().length() != 0) {
			str.append(", ");
			str.append(client.getNrStrada());
		}

		return str.toString();
	}

}
