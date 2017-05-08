package utils;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MailOperations {

	private static final Logger logger = LogManager.getLogger(MailOperations.class);

	public static void sendMail(String mailMessage) {

		String to = "florin.brasoveanu@arabesque.ro";
		String from = "FlotaWeb";
		String host = "mail.arabesque.ro";

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(properties);

		try {
			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject("FlotaWeb");

			message.setText(mailMessage);

			Transport.send(message);

		} catch (MessagingException e) {
			logger.error(Utils.getStackTrace(e));
		}
	}

}
