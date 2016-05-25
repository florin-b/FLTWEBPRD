package async;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Traseu;
import helpers.HelperEvenimente;
import model.CalculeazaTraseu;

public class EvenimenteAsync implements Runnable {

	private AsyncContext context;

	public EvenimenteAsync(AsyncContext context) {
		this.context = context;
	}

	@Override
	public void run() {

		HttpServletResponse response = (HttpServletResponse) context.getResponse();

		try {
			response.getWriter().write(getEvenimente());
		} catch (IOException e) {
			
		}

		context.complete();

	}

	private String getEvenimente() {
		Traseu traseu = new Traseu();

		try {

			HttpServletRequest httpRequest = (HttpServletRequest) context.getRequest();

			String[] infoBorderou = httpRequest.getParameter("codBorderou").split(",");
			String codBorderou = infoBorderou[0];

			CalculeazaTraseu calculeaza = new CalculeazaTraseu();
			traseu = calculeaza.getEvenimenteTraseu(codBorderou);

			HttpSession session = httpRequest.getSession();
			session.setAttribute("codBorderou", codBorderou);

			session.setAttribute("startTraseu", traseu.getSumarTraseu().getDataStart());
			session.setAttribute("stopTraseu", traseu.getSumarTraseu().getDataStop());

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		String strEvenimente = "";
		String strSumar = "";

		if (traseu.getEvenimenteTraseu() != null)
			strEvenimente = HelperEvenimente.formatEvenimente(traseu.getEvenimenteTraseu());

		if (traseu.getSumarTraseu() != null)
			strSumar = HelperEvenimente.formatSumar(traseu.getSumarTraseu());

		String strTraseu = strEvenimente + "#" + strSumar;

		return strTraseu;

	}

}
