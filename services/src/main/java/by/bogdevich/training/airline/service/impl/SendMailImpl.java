package by.bogdevich.training.airline.service.impl;

import org.junit.Ignore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import by.bogdevich.training.airline.service.SemdMail;

@Ignore
@Async
@Service("crunchifyEmail")
public class SendMailImpl implements SemdMail {
	
	@Autowired
	private MailSender crunchifymail; // MailSender interface defines a strategy
										// for sending simple mails
 
	@Override
	public void crunchifyReadyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
 
		SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
		crunchifyMsg.setFrom(fromAddress);
		crunchifyMsg.setTo(toAddress);
		crunchifyMsg.setSubject(subject);
		crunchifyMsg.setText(msgBody);
		crunchifymail.send(crunchifyMsg);
	}

}
