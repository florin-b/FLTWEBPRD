package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.Borderou;
import database.OperatiiSoferi;
import utils.Formatting;
import utils.Utils;

@WebServlet(urlPatterns = "/getBorderouri.do")
public class GetBorderouri extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(GetBorderouri.class);

	public GetBorderouri() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(getBorderouri(request));

	}

	private String getBorderouri(HttpServletRequest request) {

		String codSofer = request.getParameter("codSofer");
		String dataStart = request.getParameter("startInterval");
		String dataStop = request.getParameter("stopInterval");

		OperatiiSoferi operatiiSoferi = new OperatiiSoferi();
		List<Borderou> listBorderouri = null;

		try {
			listBorderouri = operatiiSoferi.getBorderouri(codSofer, Formatting.simpleDateFormat(dataStart), Formatting.simpleDateFormat(dataStop));
		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		StringBuilder option = new StringBuilder();

		option.append("<select id=\"borderouri\" name=\"borderouri\"  size=5>");

		int i = 0;
		for (Borderou borderou : listBorderouri) {
			option.append("<option value=");
			option.append(borderou.getCod());
			option.append(",");
			option.append(borderou.isActiv());

			if (i == 0)
				option.append(" selected>");
			else
				option.append(" >");

			option.append(borderou.getCod());
			option.append(" / ");
			option.append(borderou.getDataEmitere());
			option.append("</option>");
			i++;
		}

		option.append("</select>");

		return option.toString();

	}

}
