package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ClientNelivrat;
import database.OperatiiSoferi;
import helpers.HelperEvenimente;
import model.SendSmsClient;

/**
 * Servlet implementation class GetClientiBorderou
 */
@WebServlet("/getClientiNelivrati.do")
public class GetClientiNelivrati extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetClientiNelivrati() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(getCodBorderou(request));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String getCodBorderou(HttpServletRequest request) {
		String codSofer = request.getParameter("codSofer");

		OperatiiSoferi opSoferi = new OperatiiSoferi();

		List<ClientNelivrat> clientiNelivrati = null;
		try {
			clientiNelivrati = opSoferi.getClientiBordNelivrati(codSofer);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		request.getSession().setAttribute("clientiNelivrati", clientiNelivrati);
		
		return HelperEvenimente.formatClientiNelivrati(clientiNelivrati);
	}

}
