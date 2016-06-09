package by.bogdevich.training.airline.webapp.page.info.panel;


import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import by.bogdevich.training.airline.datamodel.Ticket;

public class TicketInfoPanel extends Panel {
	

	private Ticket ticket;
	

	public TicketInfoPanel(String id, Ticket ticket) {
		super(id);
		this.ticket = ticket;
	}


	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Label("user-firstname", ticket.getUserProfile().getFirstName()));
		add(new Label("user-lastname", ticket.getUserProfile().getLastName()));
		add(new Label("user-pasport-number", ticket.getUserProfile().getPassportNumber()));
		add(new Label("user-ticket-class", ticket.getTicketClass()));
		add(new Label("user-weight-baggage", ticket.getWeightBaggage()));
		add(new CheckBox("user-priority-seats", Model.of(ticket.getPrioritySeats())).setEnabled(false));
		add(new CheckBox("user-priority-registr", Model.of(ticket.getPriorityRegistration())).setEnabled(false));
		add(new CheckBox("user-for-baby", Model.of(ticket.getForBaby())).setEnabled(false));
		add(new Label("user-date-bought", ticket.getDateBought()));
		add(new Label("user-cost", ticket.getCosts()));
		
		
	}

}
