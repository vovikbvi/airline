package by.bogdevich.training.airline.service;

public interface SemdMail {

	void crunchifyReadyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody);

}