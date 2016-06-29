package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Localizare;

@WebServlet("/getPozitieMasini.do")
public class GetPozitieMasini extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetPozitieMasini() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(getPozitieMasini(request));
	}

	private String getPozitieMasini(HttpServletRequest request) {
		String listMasini = "'" + request.getParameter("listMasini").replace(",", "','") + "'";

		String listPozitii = "";

		if (listMasini.length() > 0) {
			Localizare localizare = new Localizare();
			try {
				listPozitii = localizare.getPozitieMasini(listMasini);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return listPozitii;

	}

}
