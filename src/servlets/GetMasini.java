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

import beans.Masina;
import database.OperatiiFiliala;
import database.OperatiiTraseu;
import utils.Utils;

/**
 * Servlet implementation class GetMasini
 */
@WebServlet("/getMasini.do")
public class GetMasini extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(OperatiiTraseu.class);

	public GetMasini() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(getListMasini(request));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String getListMasini(HttpServletRequest request) {
		String codFiliala = request.getParameter("filialaSel");
		String isMultiple = request.getParameter("multiple");

		String optMultiple = "  ";

		if (isMultiple != null && isMultiple.equals("1"))
			optMultiple = " multiple ";

		OperatiiFiliala operatiiFiliala = new OperatiiFiliala();
		List<Masina> listMasini = null;

		try {
			listMasini = operatiiFiliala.getMasiniFiliala(codFiliala);
		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		StringBuilder option = new StringBuilder();
		option.append("<select id=\"masini\" name=\"masini\" " + optMultiple + "  size=10>");
		for (Masina oMasina : listMasini) {
			option.append("<option value=");
			option.append(oMasina.getNrAuto());
			option.append(">");
			option.append(oMasina.getNrAuto());
			option.append("</option>");
		}
		option.append("</select>");

		return option.toString();

	}

}
