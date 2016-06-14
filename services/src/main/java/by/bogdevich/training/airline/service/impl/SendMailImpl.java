package by.bogdevich.training.airline.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;

import by.bogdevich.training.airline.service.SemdMail;


public class SendMailImpl implements SemdMail {
	
	
	@Autowired
	private MailSender crunchifymail;
										
	@Async
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
