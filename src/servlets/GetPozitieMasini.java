package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Localizare;

/**
 * Servlet implementation class GetPozitieMasini
 */
@WebServlet("/getPozitieMasini.do")
public class GetPozitieMasini extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetPozitieMasini() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String listMasini = request.getParameter("listMasini");

		String listPozitii = "";

		if (listMasini.length() > 0) {
			Localizare localizare = new Localizare();
			listPozitii = localizare.getPozitieMasini(listMasini);

		}
		response.getWriter().write(listPozitii);
	}

}
