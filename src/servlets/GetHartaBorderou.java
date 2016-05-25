package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.PozitieClient;
import beans.TraseuBorderou;
import database.OperatiiTraseu;

/**
 * Servlet implementation class GetHartaBorderou
 */
@WebServlet("/getHartaBorderou.do")
public class GetHartaBorderou extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetHartaBorderou() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String codBorderou = (String) session.getAttribute("codBorderou");
		
		String startBorderou = (String) session.getAttribute("startTraseu");
		String stopBorderou = (String) session.getAttribute("stopTraseu");

		OperatiiTraseu operatiiTraseu = new OperatiiTraseu();
		List<PozitieClient> pozitiiClienti = operatiiTraseu.getCoordClientiBorderou(codBorderou);
		List<TraseuBorderou> traseuBorderou = operatiiTraseu.getTraseuBorderou(codBorderou, startBorderou, stopBorderou);

		StringBuilder strPozitii = new StringBuilder();

		for (PozitieClient client : pozitiiClienti) {
			strPozitii.append(client.getLatitudine());
			strPozitii.append(",");
			strPozitii.append(client.getLongitudine());
			strPozitii.append(",");
			strPozitii.append(client.getNumeClient());
			strPozitii.append("#");
		}

		StringBuilder strTraseu = new StringBuilder();

		for (TraseuBorderou traseu : traseuBorderou) {
			if (traseu.getViteza() > 0) {
				strTraseu.append(traseu.getLatitudine());
				strTraseu.append(",");
				strTraseu.append(traseu.getLongitudine());
				strTraseu.append("#");
			}
		}

		StringBuilder strResult = new StringBuilder();
		strResult.append(strTraseu.toString());
		strResult.append("--");
		strResult.append(strPozitii.toString());

		response.getWriter().write(strResult.toString());
	}

}