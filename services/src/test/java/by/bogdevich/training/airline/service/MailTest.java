package by.bogdevich.training.airline.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.service.util.SendMail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class MailTest {

	//@Test
	public void mailTest(){
		
		SendMail sender = new SendMail("vovikbvi@gmail.com", "sobaka123");
		sender.send("subject", "text", "vovikbvi@gmail.com", "vovikbvi@tut.by");
	}
	
}
