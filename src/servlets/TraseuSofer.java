package servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.OperatiiTraseu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/TraseuSofer.do")
public class TraseuSofer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(TraseuSofer.class);

	public TraseuSofer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String masina = request.getParameter("masina").replace("-", "");
		String dataStart = request.getParameter("startInterval");
		String dataStop = request.getParameter("stopInterval");

		OperatiiTraseu traseu = new OperatiiTraseu();
		String strTraseu = "";
		try {
			strTraseu = traseu.getTraseuInterval(masina, dataStart, dataStop);
		} catch (SQLException e) {
			logger.error(e.toString());

		}

		PrintWriter writer = response.getWriter();
		writer.write(strTraseu);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
