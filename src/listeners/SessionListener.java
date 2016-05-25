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
		logToFile();

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		activeSessions--;

	}

	private void logToFile() {

		String xmlFilePath = "c://logs/SessionLog.txt";

		DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		
		Date date = new Date();
		
		File outputFile = new File(xmlFilePath);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile, true))) {
			writer.write("Active sessions at " + df.format(date) + " : " + activeSessions);
			writer.newLine();
			writer.flush();

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
