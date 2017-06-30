package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import beans.BeanSmsEmis;
import helpers.HelperSMS;
import model.OperatiiSms;
import utils.Formatting;
import utils.Utils;

/**
 * Servlet implementation class AnalizaSmsClienti
 */
@WebServlet("/analizaSmsClienti.do")
public class AnalizaSmsClienti extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(AnalizaSmsClienti.class);

	public AnalizaSmsClienti() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String dataSMS = request.getParameter("dataSmsTrimis");

		String filiala = request.getParameter("filSel");

		PrintWriter writer = response.getWriter();

		OperatiiSms opSms = new OperatiiSms();

		List<BeanSmsEmis> listSms = null;

		try {
			listSms = opSms.getSmsEmis(Formatting.simpleDateFormat(dataSMS), filiala);
		} catch (SQLException e) {
			logger.error(Utils.getStackTrace(e));
		}

		writer.write(HelperSMS.formatAnalizaSMS(listSms));

	}

}
