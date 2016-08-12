package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Traseu;
import database.OperatiiTraseu;
import helpers.HelperEvenimente;
import model.CalculeazaTraseu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(urlPatterns = "/getEvenimenteBorderou.do")
public class GetEvenimenteBorderou extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(OperatiiTraseu.class);

	public GetEvenimenteBorderou() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(getEvenimente(request));

	}

	private String getEvenimente(HttpServletRequest request) {
		Traseu traseu = new Traseu();

		try {

			String[] infoBorderou = request.getParameter("codBorderou").split(",");
			String codBorderou = infoBorderou[0];

			CalculeazaTraseu calculeaza = new CalculeazaTraseu();
			traseu = calculeaza.getEvenimenteTraseu(codBorderou);

			HttpSession session = request.getSession();
			session.setAttribute("codBorderou", codBorderou);

			session.setAttribute("startTraseu", traseu.getSumarTraseu().getDataStart());
			session.setAttribute("stopTraseu", traseu.getSumarTraseu().getDataStop());

		} catch (Exception e) {
			logger.error(e.toString());
		}

		String strEvenimente = "";
		String strSumar = "";

		if (traseu.getEvenimenteTraseu() != null)
			strEvenimente = HelperEvenimente.formatEvenimente(traseu.getEvenimenteTraseu());

		if (traseu.getSumarTraseu() != null)
			strSumar = HelperEvenimente.formatSumarBorderou(traseu.getSumarTraseu());

		String strTraseu = strEvenimente + "#" + strSumar;

		return strTraseu;

	}

}
