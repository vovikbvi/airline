package by.bogdevich.training.airline.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.service.PrintTicket;
import by.bogdevich.training.airline.service.TicketService;

@Service
public class PrintTicketImpl implements PrintTicket {
	
 	@Override
	public void CreatePdf(Ticket ticket, Flight flight) throws FileNotFoundException, DocumentException{
	Document document = new Document(PageSize.A4, 50, 50, 50, 50);
	Font fontTitle = new Font(Font.FontFamily.HELVETICA, 32, Font.BOLD);
	PdfWriter.getInstance(document, new FileOutputStream("TICKET.pdf"));
	document.open();

	// отцентрированный параграф
	Paragraph title = new Paragraph("Air ticket", fontTitle);
	title.setAlignment(Element.ALIGN_CENTER);
	title.setSpacingAfter(20);
	document.add(title);


	PdfPTable content = new PdfPTable(4);
	PdfPCell cell;
	cell = new PdfPCell(new Phrase(ticket.getUserProfile().getFirstName()));
	cell.setBorder(0);
	content.addCell(cell);

	cell = new PdfPCell(new Phrase(ticket.getUserProfile().getLastName()));
	cell.setBorder(0);
	content.addCell(cell);

	cell = new PdfPCell();
	cell.setBorder(0);	
	content.addCell(cell);

	document.add(content);
	
	document.close();

}
}
