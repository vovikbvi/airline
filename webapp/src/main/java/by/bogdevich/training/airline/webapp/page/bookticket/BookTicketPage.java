package by.bogdevich.training.airline.webapp.page.bookticket;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEventSink;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.component.IRequestablePage;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.datamodel.TicketTupe;
import by.bogdevich.training.airline.service.TicketService;
import by.bogdevich.training.airline.webapp.app.AuthorizedSession;
import by.bogdevich.training.airline.webapp.common.renderer.TicketClassChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.renderer.TicketTupeChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.payment.PaymentPage;

@AuthorizeInstantiation(value = { "ADMIN", "OPERATOR", "USER" })
public class BookTicketPage extends AbstractPage {

	@Inject
	private TicketService ticketService;

	private Ticket ticket;

	private Flight flight;

	public BookTicketPage() {
		super();
	}

	public BookTicketPage(Ticket ticket, Flight flight) {
		super();
		this.ticket = ticket;
		this.flight = flight;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form form = new Form("form", new CompoundPropertyModel<Ticket>(ticket));
		add(form);

		TextField<Integer> numberSeatsField = new TextField<>("numberSeats");
		numberSeatsField.setRequired(true);
		form.add(numberSeatsField);

		CheckBox baggageField = new CheckBox("baggage");
		form.add(baggageField);

		TextField<Double> weightBaggageField = new TextField<>("weightBaggage");
		form.add(weightBaggageField);
		weightBaggageField.setEnabled(false);

		baggageField.add(new AjaxEventBehavior("change") {
			@Override
			protected void onEvent(AjaxRequestTarget target) {
				if (baggageField.getOutputMarkupId()){
					System.out.println("!!!!!!!!!!!!! ypa !!!!!!!!!!!!!!!!!!!!!!");
				}
			}
		});
		
		
		DropDownChoice<TicketTupe> ticketTupeField = new DropDownChoice<>("ticketTupe",
				Arrays.asList(TicketTupe.values()), TicketTupeChoiceRenderer.INSTANCE);
		ticketTupeField.setRequired(true);
		form.add(ticketTupeField);

		DropDownChoice<TicketClass> ticketClassField = new DropDownChoice<>("ticketClass",
				Arrays.asList(TicketClass.values()), TicketClassChoiceRenderer.INSTANCE);
		ticketClassField.setRequired(true);
		form.add(ticketClassField);

		CheckBox priorityRegistrationField = new CheckBox("priorityRegistration");
		form.add(priorityRegistrationField);

		CheckBox prioritySeatsField = new CheckBox("prioritySeats");
		form.add(prioritySeatsField);

		CheckBox forBabyField = new CheckBox("forBaby");
		form.add(forBabyField);

		ticket.setFlight(flight);
		ticket.setUserProfile(AuthorizedSession.get().getLoggedUser());
		ticket.setDateBought(new Date());
		/*
		 * if ((ticket.getWeightBaggage() != null) && (ticket.getWeightBaggage()
		 * != 0.0)){ ticket.setBaggage(true); }else{ ticket.setBaggage(false); }
		 */
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();

				try {
					ticketService.insert(ticket);
					setResponsePage(new PaymentPage(ticket));
				} catch (IllegalArgumentException e) {
					error(e.getMessage());
				}
			}
		});

		add(new FeedbackPanel("feedback"));

	}

}