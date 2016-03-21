package hobotun.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import hobotun.core.ApplicationProperties;

public class SendEmail {

	private static SendEmail instance;

	private final static String host = ApplicationProperties.getInstance().getPropertyValue("ru.hobotun.smtp.host"); //"mail.meerson.ru";
	private final static String port = ApplicationProperties.getInstance().getPropertyValue("ru.hobotun.smtp.port");//"465";//"25"; //"587";
	private final static String username = ApplicationProperties.getInstance().getPropertyValue("ru.hobotun.smtp.username");//"info@meerson.ru";
	private final static String password = ApplicationProperties.getInstance().getPropertyValue("ru.hobotun.smtp.password");//"q1w2e3r4t5y6u7i8o9p0";
	private final static String from = ApplicationProperties.getInstance().getPropertyValue("ru.hobotun.smtp.from");//"info@meerson.ru";

	private SendEmail() {

	}

	public static SendEmail getInstance() {
		if (instance == null) {
			synchronized (SendEmail.class) {
				if (instance == null) {
					instance = new SendEmail();
				}
			}
		}

		return instance;
	}

	public void SendMail(String to, String title, String body) {

		// Get system properties
		Properties properties = System.getProperties();



		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.charset", "UTF-8");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(title);

			// Send the actual HTML message, as big as you like
			message.setContent(body, "text/html; charset=UTF-8");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}
