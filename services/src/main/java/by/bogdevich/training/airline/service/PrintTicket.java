package by.bogdevich.training.airline.service;

import java.io.FileNotFoundException;

import com.itextpdf.text.DocumentException;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;

public interface PrintTicket {

	void CreatePdf(Ticket ticket) throws FileNotFoundException, DocumentException;

}