package async;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Sofer;
import database.OperatiiSoferi;
import utils.MailOperations;

public class SoferiAsync implements Runnable {

	private AsyncContext context;

	public SoferiAsync(AsyncContext context) {
		this.context = context;
	}

	@Override
	public void run() {

		HttpServletResponse response = (HttpServletResponse) context.getResponse();

		response.setHeader("Cache-Control", "no-chache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		try {
			response.getWriter().write(listSoferi());
		} catch (IOException e) {
			MailOperations.sendMail(e.toString());
		}

		context.complete();

	}

	private String listSoferi() {

		HttpServletRequest httpRequest = (HttpServletRequest) context.getRequest();

		String filiala = httpRequest.getParameter("filialaSel");

		OperatiiSoferi operatiiSoferi = new OperatiiSoferi();

		List<Sofer> listSoferi = null;

		try {
			listSoferi = operatiiSoferi.getListSoferi(filiala);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		StringBuilder option = new StringBuilder();
		option.append("<select id=\"soferi\" name=\"soferi\"  size=10>");

		for (Sofer unSofer : listSoferi) {
			option.append("<option value=");
			option.append(unSofer.getCodSofer());
			option.append(">");
			option.append(unSofer.getNumeSofer());
			option.append("</option>");
		}

		option.append("</select>");

		return option.toString();
	}

}
