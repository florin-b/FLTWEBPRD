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

@WebServlet(urlPatterns = "/getSoferi.do", asyncSupported = true)
public class GetSoferi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetSoferi() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(getListSoferi(request));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}

	private String getListSoferi(HttpServletRequest request) {
		String filiala = request.getParameter("filialaSel");

		OperatiiSoferi operatiiSoferi = new OperatiiSoferi();

		List<Sofer> listSoferi = null;

		try {
			listSoferi = operatiiSoferi.getListSoferi(filiala);
		} catch (SQLException e) {
			e.printStackTrace();
		}

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

		return option.toString();
	}

}
