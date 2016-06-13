package by.bogdevich.training.airline.webapp.page.bookticket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.service.TicketService;
import by.bogdevich.training.airline.webapp.app.AuthorizedSession;
import by.bogdevich.training.airline.webapp.common.events.BookTicketChangeEvent;
import by.bogdevich.training.airline.webapp.common.renderer.TicketClassChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.payment.PaymentPage;

@AuthorizeInstantiation(value = { "ADMIN", "OPERATOR", "PASSANGER" })
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

		ticket.setFlight(flight);
		ticket.setUserProfile(AuthorizedSession.get().getLoggedUser());
		ticket.setDateBought(new Date());
		ticket.setPaid(false);

		Form form = new Form("form", new CompoundPropertyModel<Ticket>(ticket));
		add(form);

	
		
        DropDownChoice<Integer> numberSeatsField = new DropDownChoice<Integer>("numberSeats", getListSeats());
        numberSeatsField.setOutputMarkupId(true);
        numberSeatsField.setOutputMarkupPlaceholderTag(true);
        numberSeatsField.setRequired(true);
        form.add(numberSeatsField);
		
        
		DropDownChoice<TicketClass> ticketClassField = new DropDownChoice<>("ticketClass",
				Arrays.asList(TicketClass.values()), TicketClassChoiceRenderer.INSTANCE);
		ticketClassField.setRequired(true);
		form.add(ticketClassField);
	
		ticketClassField.add(new AjaxFormComponentUpdatingBehavior("onchange")
        {
            @Override
            protected void onUpdate(AjaxRequestTarget target)
            {
            	numberSeatsField.setChoices(getListSeats());
                target.add(numberSeatsField);
            }
        });
           
        
		CheckBox baggageField = new CheckBox("baggage");
		form.add(baggageField);

		TextField<Double> weightBaggageField = new TextField<>("weightBaggage");
		form.add(weightBaggageField);
		weightBaggageField.add(RangeValidator.<Double> range(0d, 1_000_000d));
		weightBaggageField.setEnabled(false);

		baggageField.add(new AjaxFormComponentUpdatingBehavior("change") {

			@Override
			protected void onUpdate(AjaxRequestTarget target) {
				if (baggageField.getModelObject()) {
					weightBaggageField.setEnabled(true);
					weightBaggageField.setRequired(true);
					target.add(weightBaggageField);
				} else {
					weightBaggageField.setEnabled(false);
					target.add(weightBaggageField);
				}
			}
		});
		weightBaggageField.setOutputMarkupPlaceholderTag(true);

		CheckBox priorityRegistrationField = new CheckBox("priorityRegistration");
		form.add(priorityRegistrationField);

		CheckBox prioritySeatsField = new CheckBox("prioritySeats");
		form.add(prioritySeatsField);

		CheckBox forBabyField = new CheckBox("forBaby");
		form.add(forBabyField);


		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();

			try {
					ticketService.insert(ticket);
					setResponsePage(new PaymentPage(ticket));
				} catch (IllegalArgumentException e) {
					error(getString("ui.luggage_full"));
				}
			}
		});

		add(new FeedbackPanel("feedback"));
		}

	
	private ArrayList<Integer> getListSeats() {
		ArrayList<Integer> listSeats= new ArrayList<Integer>(ticketService.getListEmtySeats(ticket));
		return listSeats;
	}
}
