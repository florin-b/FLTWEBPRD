package async;

import java.io.IOException;
import java.util.List;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Borderou;
import database.OperatiiSoferi;
import utils.Formatting;

public class BorderouriAsync implements Runnable {

	private AsyncContext context;

	public BorderouriAsync(AsyncContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		HttpServletResponse response = (HttpServletResponse) context.getResponse();

		response.setHeader("Cache-Control", "no-chache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		try {
			response.getWriter().write(getBorderouri());
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.complete();

	}

	private String getBorderouri() {

		HttpServletRequest httpRequest = (HttpServletRequest) context.getRequest();
		

		String codSofer = httpRequest.getParameter("codSofer");
		String dataStart = httpRequest.getParameter("startInterval");
		String dataStop = httpRequest.getParameter("stopInterval");

		OperatiiSoferi operatiiSoferi = new OperatiiSoferi();
		List<Borderou> listBorderouri = null;

		listBorderouri = operatiiSoferi.getBorderouri(codSofer, Formatting.dateFormat(dataStart), Formatting.dateFormat(dataStop));

		StringBuilder option = new StringBuilder();

		option.append("<select id=\"borderouri\" name=\"borderouri\"  size=5>");

		int i = 0;
		for (Borderou borderou : listBorderouri) {
			option.append("<option value=");
			option.append(borderou.getCod());
			option.append(",");
			option.append(borderou.isActiv());

			if (i == 0)
				option.append(" selected>");
			else
				option.append(" >");

			option.append(borderou.getCod());
			option.append(" / ");
			option.append(borderou.getDataEmitere());
			option.append("</option>");
			i++;
		}

		option.append("</select>");

		return option.toString();

	}

}
