package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.PozitieClient;
import beans.RezultatTraseu;
import beans.TraseuBorderou;
import database.OperatiiTraseu;
import model.CalculeazaTraseu;
import utils.Utils;

/**
 * Servlet implementation class GetEvenimenteBorderou
 */
@WebServlet("/getEvenimenteBorderou.do")
public class GetEvenimenteBorderou extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetEvenimenteBorderou() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String codBorderou = request.getParameter("codBorderou");
		Set<RezultatTraseu> rezultat = null;

		OperatiiTraseu operatiiTraseu = new OperatiiTraseu();

		List<TraseuBorderou> traseuBorderou = operatiiTraseu.getTraseuBorderou(codBorderou);
		List<PozitieClient> pozitiiClienti = operatiiTraseu.getCoordClientiBorderou(codBorderou);

		HttpSession session = request.getSession();
		session.setAttribute("traseuBorderou", traseuBorderou);
		session.setAttribute("pozitiiClienti", pozitiiClienti);

		CalculeazaTraseu calculeaza = new CalculeazaTraseu();
		calculeaza.setPozitiiClienti(pozitiiClienti);
		calculeaza.setTraseuBorderou(traseuBorderou);

		rezultat = calculeaza.getStareTraseu();

		response.getWriter().write(displayFormattedResults(rezultat));

	}

	private String displayFormattedResults(Set<RezultatTraseu> rezultat) {

		StringBuilder strResult = new StringBuilder();

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
			strResult.append(traseu.getNumeClient());
			strResult.append("</td>");

			strResult.append("<td>");
			strResult.append(traseu.getCodClient());
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

		return strResult.toString();

	}

	private String validareSosire(RezultatTraseu traseu) {
		if (traseu.getSosire() == null)
			return "Aprox. " + String.valueOf(traseu.getDistantaCamion()) + " km";

		return traseu.getSosire().getData();

	}

	private String validarePlecare(RezultatTraseu traseu) {
		if (traseu.getPlecare() == null)
			return "";
		return traseu.getPlecare().getData();

	}

}
