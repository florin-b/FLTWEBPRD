package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OperatiiTablete;

@WebServlet("/operatiiTablete.do")
public class ServletTablete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletTablete() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter writer = response.getWriter();

		String codSofer = request.getParameter("codSofer");
		String codTableta = request.getParameter("codTableta");
		String operatie = request.getParameter("operatie");

		OperatiiTablete opTableta = new OperatiiTablete();
		try {
			opTableta.gestioneazaCod(codTableta, codSofer, operatie);
		} catch (SQLException e) {
			writer.write(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
