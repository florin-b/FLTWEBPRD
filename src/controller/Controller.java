package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import beans.User;
import database.Account;
import utils.Constants;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DataSource ds;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		try {
			InitialContext initContext = new InitialContext();
			Context env = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) env.lookup("jdbc/myoracle_prod");

		} catch (NamingException e) {
			throw new ServletException();
		}

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		Connection conn = null;

		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			throw new ServletException();
		}

		Account account = new Account(conn);

		if (action.equals("dologin")) {

			String name = request.getParameter("name");
			String password = request.getParameter("password");

			request.setAttribute("name", name);
			request.setAttribute("password", "");
			request.setAttribute("message", "");

			ApplicationContext context = new ClassPathXmlApplicationContext(Constants.BEANS_XML);

			User user = (User) context.getBean("user");
			user.setName(name);
			user.setPassword(password);

			try {
				HttpSession session = request.getSession();
				session.setAttribute("userAuthLevel", "0");

				if (account.loginUser(user)) {
					session.setAttribute("userAuthLevel", "1");
					session.setAttribute("user", user);
					request.getRequestDispatcher("/main.jsp").forward(request, response);
				} else {
					session.setAttribute("account", account);
					session.setAttribute("user", user);
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				request.setAttribute("email", "Eroare conectare baza de date.");
			}

			((ClassPathXmlApplicationContext) context).close();

		}

		else if (action.equals("dologout")) {
			request.getSession().invalidate();
			request.setAttribute("name", "");
			request.setAttribute("password", "");
			request.setAttribute("message", "");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

}
