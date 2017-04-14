package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanStareTableta;
import helpers.HelperSMS;
import helpers.HelperTableta;
import model.OperatiiTablete;

@WebServlet("/getTablete.do")
public class GetTablete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetTablete() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codSofer = request.getParameter("codSofer");

		PrintWriter writer = response.getWriter();

		writer.write(HelperTableta.formatTableteSoferi(getTableteSoferi(codSofer)));

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	private List<BeanStareTableta> getTableteSoferi(String codSofer) {
		List<BeanStareTableta> tablete = new ArrayList<>();
		OperatiiTablete opTablete = new OperatiiTablete();
		try {
			tablete = opTablete.getTableteSoferi(codSofer);
		} catch (SQLException e) {

			System.out.println(e.toString());
		}

		return tablete;
	}

}
