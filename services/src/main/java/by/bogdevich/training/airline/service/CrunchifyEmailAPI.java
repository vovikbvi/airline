package by.bogdevich.training.airline.service;

public interface CrunchifyEmailAPI {

	void crunchifyReadyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody);

}