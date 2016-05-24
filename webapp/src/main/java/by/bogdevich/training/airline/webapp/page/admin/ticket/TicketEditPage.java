package by.bogdevich.training.airline.webapp.page.admin.ticket;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.service.TicketService;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

public class TicketEditPage extends AbstractPage {

	@Inject
	private TicketService ticketService;

	private Ticket ticket;

	public TicketEditPage() {
		super();
	}

	public TicketEditPage(Ticket ticket) {
		super();
		this.ticket = ticket;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		
		
	}
}
