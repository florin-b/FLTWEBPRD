package listeners;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	private static int activeSessions;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		activeSessions++;
		

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		activeSessions--;

	}

	

}
