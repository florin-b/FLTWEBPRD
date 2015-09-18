package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.OperatiiFiliala;

/**
 * Servlet implementation class getFiliale
 */
@WebServlet("/getFiliale.do")
public class GetFiliale extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetFiliale() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OperatiiFiliala operatiiFiliala = new OperatiiFiliala();
		HttpSession session = request.getSession();
		try {
			session.setAttribute("filiale", operatiiFiliala.getListFiliale());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/pozitieMasiniOptiuni.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
