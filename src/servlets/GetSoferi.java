package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Sofer;
import database.OperatiiSoferi;

/**
 * Servlet implementation class GetSoferi
 */
@WebServlet("/getSoferi.do")
public class GetSoferi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetSoferi() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filiala = request.getParameter("filialaSel");

		OperatiiSoferi operatiiSoferi = new OperatiiSoferi();

		List<Sofer> listSoferi = null;

		listSoferi = operatiiSoferi.getListSoferi(filiala);

		StringBuilder option = new StringBuilder();
		option.append("<select id=\"soferi\" name=\"soferi\"  size=10>");

		for (Sofer unSofer : listSoferi) {
			option.append("<option value=");
			option.append(unSofer.getCodSofer());
			option.append(">");
			option.append(unSofer.getNumeSofer());
			option.append("</option>");
		}

		option.append("</select>");

		response.setHeader("Cache-Control", "no-chache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		response.getWriter().write(option.toString());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
