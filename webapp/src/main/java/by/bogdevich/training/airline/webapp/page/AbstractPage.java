package by.bogdevich.training.airline.webapp.page;

import java.io.FileNotFoundException;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import com.itextpdf.text.DocumentException;

import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.service.PrintTicket;
import by.bogdevich.training.airline.webapp.component.licalization.LanguageSelectionComponent;
import by.bogdevich.training.airline.webapp.component.menu.MenuUser;
import by.bogdevich.training.airline.webapp.page.home.HomePage;



public abstract class AbstractPage extends WebPage {
	
	@Inject
	private PrintTicket printTicket;


	public AbstractPage() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new LanguageSelectionComponent("language-select"));
		add(new MenuUser("menu-user"));
		
        add(new Link("home-page") {
            @Override
            public void onClick() {
                setResponsePage(new HomePage());
            }
        });

	}
	
	protected void CreatePdfTicket(Ticket ticket) {
		try {
			printTicket.CreatePdf(ticket);	
		} catch (FileNotFoundException e) {
			warn("Error PDF");
		} catch (DocumentException e) {
			warn("Error PDF");
		}
	}

}
