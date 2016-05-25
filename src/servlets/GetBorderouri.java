package servlets;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import async.BorderouriAsync;
import listeners.AppAsyncListener;

@WebServlet(urlPatterns = "/getBorderouri.do", asyncSupported = true)
public class GetBorderouri extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetBorderouri() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleService(request, response);

	}

	private void handleService(HttpServletRequest request, HttpServletResponse response) {

		AsyncContext asyncContext = request.startAsync(request, response);
		asyncContext.addListener(new AppAsyncListener());

		BorderouriAsync bord = new BorderouriAsync(asyncContext);
		asyncContext.start(bord);
		
	}

}
