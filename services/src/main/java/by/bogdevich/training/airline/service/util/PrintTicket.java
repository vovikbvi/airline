package by.bogdevich.training.airline.service.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PrintTicket {


public void test() throws FileNotFoundException, DocumentException{
	Document document = new Document(PageSize.A4, 50, 50, 50, 50);
	
	Font font1 = new Font(Font.FontFamily.HELVETICA, 32, Font.BOLD);
	
	PdfWriter.getInstance(document, new FileOutputStream("template.pdf"));

	document.open();

	// отцентрированный параграф
	Paragraph title = new Paragraph("Receipt", font1);
	title.setAlignment(Element.ALIGN_CENTER);
	title.setSpacingAfter(32);
	document.add(title);

	document.close();
}
}
