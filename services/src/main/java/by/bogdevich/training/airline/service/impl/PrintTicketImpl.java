package by.bogdevich.training.airline.service.impl;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.service.FlightService;
import by.bogdevich.training.airline.service.PrintTicket;
import by.bogdevich.training.airline.service.TicketService;

@Service
public class PrintTicketImpl implements PrintTicket {
	
@Inject
private FlightService flightService;

//@Inject
//private TicketService ticketService;

	@Async
 	@Override
	public void CreatePdf(Ticket ticket) throws FileNotFoundException, DocumentException{
 	//ticket = ticketService.getTicketWithFetch(ticket);
 	Flight flight = flightService.getFlieghtWithFetch(ticket.getFlight());
	Document document = new Document(PageSize.A5, 50, 50, 50, 50);
	Font fontTitle = new Font(Font.FontFamily.HELVETICA, 32, Font.BOLD);
	Font fontMedium = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
	Font fontSmal = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
	
	String fileName = String.format("TICKET-%s-%s.pdf",ticket.getId(), flight.getId());
	PdfWriter.getInstance(document, new FileOutputStream(fileName));
	document.open();

	
	
	Paragraph title = new Paragraph("Air ticket", fontTitle);
	title.setAlignment(Element.ALIGN_CENTER);
	title.setSpacingAfter(20);
	document.add(title);

	Paragraph paragraph;
    String startAirport = flight.getFlightCatalog().getAirportStart().getName();
	String departureTime = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(flight.getDepartureTime());
	paragraph = new Paragraph(String.format("from: %s - %s", startAirport, departureTime),fontMedium);
	document.add(paragraph);
	
	String finishAirport = flight.getFlightCatalog().getAirportFinish().getName();
	String arrivalTime = new SimpleDateFormat("yyyy-MM-dd hh:mm").format(flight.getArrivalTime());
	paragraph = new Paragraph(String.format("to: %s - %s",finishAirport, arrivalTime),fontMedium);
	document.add(paragraph);
	
	String passanger = ticket.getUserProfile().getFirstName()+" "+ticket.getUserProfile().getLastName();
	paragraph = new Paragraph(passanger,fontMedium);
	document.add(paragraph);
	
	if(ticket.getBaggage()){
	paragraph = new Paragraph("Baggage: "+ticket.getWeightBaggage().toString(),fontMedium);
	document.add(paragraph);
	}
	
	paragraph = new Paragraph("Costs: "+ticket.getCosts().toString(), fontMedium);
	document.add(paragraph);
	
	String plane = String.format("Plane: %s bort number %s",flight.getPlane().getModelPlane().getModel(),flight.getPlane().getBortNumber());
	paragraph = new Paragraph(plane, fontMedium);
	document.add(paragraph);
	
	if (ticket.getPriorityRegistration()){
	paragraph = new Paragraph("Priority registration: yes", fontSmal);
	document.add(paragraph);
	}
	
	if (ticket.getPrioritySeats()){
		paragraph = new Paragraph("Priority seats: yas", fontSmal);
		document.add(paragraph);
	}
	
	if (ticket.getForBaby()){
		paragraph = new Paragraph("for baby", fontSmal);
		document.add(paragraph);
	}
	
	document.close();
	
	    if (Desktop.isDesktopSupported()) {
	        try {
	            File myFile = new File(fileName);
	            Desktop.getDesktop().open(myFile);
	        } catch (IOException ex) {
	            // no application registered for PDFs
	        }
	    }
}
}
