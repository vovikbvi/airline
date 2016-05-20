package by.bogdevich.training.airline.service.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfGeneration {
	int FONT_SIZE_SMALL = 16;
	int FONT_SIZE_BIG = 32;
	int OFFSET = 40;

	public void test() throws DocumentException, IOException   {
		
		createTemplate();

		Receipt receipt = new Receipt("This is a veeeeeeeeeeeeeeeeeeeeee" + "eeeeeeeeeeeeeeeeeeeeery long purpose "
				+ "text, so it will overflow with font size = 16", 123.45, "Name Surname");

		fillInReceipt(receipt);
	}

	public void createTemplate() throws DocumentException, MalformedURLException, IOException  {
		Document document = new Document();

		Font font1 = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_BIG, Font.BOLD);
		Font font2 = new Font(Font.FontFamily.HELVETICA, FONT_SIZE_SMALL, Font.ITALIC | Font.UNDERLINE);

		PdfWriter.getInstance(document, new FileOutputStream("template.pdf"));

		document.open();

		// отцентрированный параграф
		Paragraph title = new Paragraph("Receipt", font1);
		title.setAlignment(Element.ALIGN_CENTER);
		title.setSpacingAfter(FONT_SIZE_BIG);
		document.add(title);

		// параграф с текстом
		Paragraph purpose = new Paragraph("Purpose", font2);
		purpose.setSpacingAfter(FONT_SIZE_BIG);
		document.add(purpose);

		// параграф с добавленным чанком текста
		Paragraph amount = new Paragraph();
		amount.setFont(font2);
		amount.setSpacingAfter(8);
		amount.add(new Chunk("Amount"));
		document.add(amount);

		// параграф с фразой, в которую добавлен чанк
		Paragraph date = new Paragraph();
		date.setFont(font2);
		Phrase phrase = new Phrase();
		phrase.add(new Chunk("Date"));
		date.add(phrase);
		document.add(date);

		document.add(new Paragraph("Name", font2));

		Paragraph footer = new Paragraph("Important - please retain for your records - ");

		// ссылка
		Anchor anchor = new Anchor("Javenue");
		anchor.setReference("http://www.javenue.info");
		footer.add(anchor);

		footer.setAlignment(Element.ALIGN_CENTER);
		footer.setSpacingBefore(FONT_SIZE_BIG);
		document.add(footer);

		// картинка, загруженная по URL
		String imageUrl = "http://www.javenue.info/files/sample.png";
		// Image.getInstance("sample.png")
		Image stamp = Image.getInstance(new URL(imageUrl));
		stamp.setAlignment(Element.ALIGN_RIGHT);
		document.add(stamp);

		document.close();
	}

	public void fillInReceipt(Receipt receipt) throws FileNotFoundException, IOException, DocumentException  {
		PdfReader reader = new PdfReader(new FileInputStream("template.pdf"));
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("receipt.pdf"));

		PdfContentByte stream = stamper.getOverContent(1);
		stream.beginText();
		stream.setColorFill(BaseColor.BLUE);

		BaseFont font = BaseFont.createFont();

		float pageWidth = reader.getPageSize(1).getWidth();
		stream.setFontAndSize(font, FONT_SIZE_SMALL);
		float v = stream.getEffectiveStringWidth(receipt.getPurpose(), false);

		float fitSize = (pageWidth - OFFSET * 2) * FONT_SIZE_SMALL / v;
		stream.setFontAndSize(font, fitSize);
		stream.setTextMatrix(OFFSET, 680);
		stream.showText(receipt.getPurpose());

		stream.setFontAndSize(font, FONT_SIZE_SMALL);

		String amount = NumberFormat.getCurrencyInstance().format(receipt.getAmount());
		v = stream.getEffectiveStringWidth(amount, false);
		stream.setTextMatrix(pageWidth - v - OFFSET, 655);
		stream.showText(amount);

		v = stream.getEffectiveStringWidth(receipt.getDate() + "", false);
		stream.setTextMatrix(pageWidth - v - OFFSET, 630);
		stream.showText(receipt.getDate() + "");

		v = stream.getEffectiveStringWidth(receipt.getName(), false);
		stream.setTextMatrix(pageWidth - v - OFFSET, 605);
		stream.showText(receipt.getName());

		stream.endText();
		stamper.setFullCompression();
		stamper.close();
	}
	
}