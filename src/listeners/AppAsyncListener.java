package listeners;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

import utils.MailOperations;

public class AppAsyncListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent arg0) throws IOException {

	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
		MailOperations.sendMail(event.getThrowable().getMessage());
	}

	@Override
	public void onStartAsync(AsyncEvent arg0) throws IOException {

	}

	@Override
	public void onTimeout(AsyncEvent arg0) throws IOException {

	}

}
