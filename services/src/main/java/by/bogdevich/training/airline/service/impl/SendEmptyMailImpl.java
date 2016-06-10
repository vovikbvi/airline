package by.bogdevich.training.airline.service.impl;


import by.bogdevich.training.airline.service.SemdMail;


public class SendEmptyMailImpl implements SemdMail {
	
										

	@Override
	public void crunchifyReadyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
 
	System.out.println("for test mail don't send");
	}

}

