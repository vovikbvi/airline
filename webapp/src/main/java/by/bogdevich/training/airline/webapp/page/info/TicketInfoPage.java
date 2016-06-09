package by.bogdevich.training.airline.webapp.page.info;

import java.io.FileNotFoundException;
import java.util.PropertyPermission;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import com.itextpdf.text.DocumentException;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.service.FlightService;
import by.bogdevich.training.airline.service.PrintTicket;
import by.bogdevich.training.airline.service.TicketService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.ticket.TicketsPage;
import by.bogdevich.training.airline.webapp.page.info.panel.FreeSeatsPanel;
import by.bogdevich.training.airline.webapp.page.info.panel.PlaneInfoPanel;
import by.bogdevich.training.airline.webapp.page.info.panel.TicketInfoPanel;


public class TicketInfoPage extends AbstractPage {

	@Inject
	private FlightService flightService;
	
	@Inject
	private TicketService ticketService;
	
	private Ticket ticket;
	
    public TicketInfoPage(Ticket ticket) {
		super();
		this.ticket = ticket;
	}

	@Override
    protected void onInitialize() {
    	super.onInitialize();
    	
    	Flight flight = flightService.getFlieghtWithFetch(ticket.getFlight());
    	ticket = ticketService.getTicketWithFetch(ticket);
    	add(new PlaneInfoPanel("plane-info-panel", flight));
    	add(new TicketInfoPanel("ticket-info-panel", ticket));
    	
        add(new Link("save-ticket") {
            @Override
            public void onClick() {
       
                CreatePdfTicket(ticket);	
            }
        });

    	
    }

}
