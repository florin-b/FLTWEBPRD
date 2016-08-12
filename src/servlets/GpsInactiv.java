package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.HelperEvenimente;
import model.OperatiiGps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.OperatiiTraseu;

@WebServlet("/GpsInactiv")
public class GpsInactiv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(OperatiiTraseu.class);

	public GpsInactiv() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(HelperEvenimente.formatGpsInactiv(getGpsInactiv(request)));

	}

	private List<beans.GpsInactiv> getGpsInactiv(HttpServletRequest request) {
		String filiala = request.getParameter("filialaSel");

		OperatiiGps operatiiGps = new OperatiiGps();

		List<beans.GpsInactiv> listGps = null;

		try {
			listGps = operatiiGps.getGpsInactiv(filiala);
		} catch (SQLException e) {
			logger.error(e.toString());
		}

		return listGps;

	}

}
