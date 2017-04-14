package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ClientNelivrat;
import model.SendSmsClient;

@WebServlet("/sendSms.do")
public class SendSms extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SendSms() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		sendSms(request);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	private void sendSms(HttpServletRequest request) {

		String smsText = request.getParameter("smsText");

		@SuppressWarnings("unchecked")
		List<ClientNelivrat> listClienti = (List<ClientNelivrat>) request.getSession().getAttribute("clientiNelivrati");

		if (listClienti == null)
			return;

		List<ClientNelivrat> uniqueList = new ArrayList<ClientNelivrat>(new HashSet<>(listClienti));

		SendSmsClient smsClient = new SendSmsClient();

		for (ClientNelivrat client : uniqueList) {
			if (client.getTelefon() != null && client.getTelefon().length() > 0)
				smsClient.sendSms(client.getTelefon(), smsText);

		}

	}

}
