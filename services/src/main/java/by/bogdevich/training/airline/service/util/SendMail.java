package by.bogdevich.training.airline.service.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	private String username;
	private String password;
	private Properties props;

	public SendMail(String username, String password) {
		this.username = username;
		this.password = password;

		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		//props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	}

	public void send(String subject, String text, String fromEmail, String toEmail) {
		Session session = Session.getInstance(props, new Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
			}
		});

		try {
			Message message = new MimeMessage(session);
			// от кого
			message.setFrom(new InternetAddress(username));
			// кому
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			// Заголовок письма
			message.setSubject(subject);
			// Содержимое
			message.setText(text);

			// Отправляем сообщение
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
