package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Borderou;
import database.OperatiiSoferi;
import utils.Formatting;

/**
 * Servlet implementation class GetBorderouri
 */
@WebServlet("/getBorderouri.do")
public class GetBorderouri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetBorderouri() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codSofer = request.getParameter("codSofer");

		String dataStart = request.getParameter("startInterval");
		String dataStop = request.getParameter("stopInterval");

		OperatiiSoferi operatiiSoferi = new OperatiiSoferi();
		List<Borderou> listBorderouri = null;

		listBorderouri = operatiiSoferi.getBorderouri(codSofer, Formatting.dateFormat(dataStart), Formatting.dateFormat(dataStop));

		StringBuilder option = new StringBuilder();

		option.append("<select id=\"borderouri\" name=\"borderouri\"  size=5>");

		int i = 0;
		for (Borderou borderou : listBorderouri) {
			option.append("<option value=");
			option.append(borderou.getCod());

			if (i == 0)
				option.append(" selected>");
			else
				option.append(" >");

			option.append(borderou.getCod());
			option.append("</option>");
			i++;
		}

		option.append("</select>");
		response.getWriter().write(option.toString());
	}

}
