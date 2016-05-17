package by.bogdevich.training.airline.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itextpdf.text.DocumentException;

import by.bogdevich.training.airline.service.util.PdfGeneration;
import by.bogdevich.training.airline.service.util.PrintTicket;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })

public class PrintTest {

	@Test
	public void testPdf(){
     PrintTicket qq = new PrintTicket();
     try {
		qq.test();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (DocumentException e) {
		e.printStackTrace();
	}
	}
	
//	@Test
	public void testPdfNet(){
		PdfGeneration qq = new PdfGeneration();
		try {
			qq.test();
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
