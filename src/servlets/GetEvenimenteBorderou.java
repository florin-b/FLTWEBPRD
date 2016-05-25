package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import async.EvenimenteAsync;
import listeners.AppAsyncListener;

@WebServlet(urlPatterns = "/getEvenimenteBorderou.do", asyncSupported = true)
public class GetEvenimenteBorderou extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetEvenimenteBorderou() {
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
		EvenimenteAsync evenimente = new EvenimenteAsync(asyncContext);
		asyncContext.start(evenimente);

	}

}
