package by.bogdevich.training.airline.webapp.page.admin.modelplane;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.datamodel.TicketTupe;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.service.ManufacturedPlainService;
import by.bogdevich.training.airline.service.ModelPlaneService;
import by.bogdevich.training.airline.service.PriceService;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.common.FlightChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.TicketClassChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.TicketTupeChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.UserProfileChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.ticket.TicketsPage;



public class ModelPlaneEditPage extends AbstractPage {
	
	@Inject
	private ModelPlaneService modelPlaneService;
	
	@Inject ManufacturedPlainService manufacturedPlainService;
	
	private ModelPlane modelPlane;
	
    public ModelPlaneEditPage() {
        super();
    }

	public ModelPlaneEditPage(ModelPlane modelPlane) {
		super();
		this.modelPlane = modelPlane;
	}

    @Override
    protected void onInitialize() {
    	super.onInitialize();
    	
		Form form = new Form("form", new CompoundPropertyModel<ModelPlane>(modelPlane));
		add(form);
  
		ArrayList<Flight> listFlight= new ArrayList<Flight>(flightService.getAll());
        DropDownChoice<Flight> flightField = new DropDownChoice<Flight>("flight", listFlight, FlightChoiceRenderer.INSTANCE);
        flightField.setRequired(true);
        form.add(flightField);
		
		CheckBox paidField = new CheckBox("paid");
		form.add(paidField);
		
		TextField<Integer> numberSeatsField = new TextField<>("numberSeats");
		numberSeatsField.setRequired(true);
		form.add(numberSeatsField);

		DateTextField dateBoughtField = new DateTextField("dateBought", "dd-MM-yyyy");
		dateBoughtField.add(new DatePicker());
        dateBoughtField.setRequired(true);
		form.add(dateBoughtField);
		
		CheckBox baggageField = new CheckBox("baggage");
		form.add(baggageField);
		
    	TextField<Double> weightBaggageField = new TextField<>("weightBaggage");
		weightBaggageField.setRequired(true);
		form.add(weightBaggageField);
		
        DropDownChoice<TicketTupe> ticketTupeField = new DropDownChoice<>("ticketTupe", Arrays.asList(TicketTupe.values()), TicketTupeChoiceRenderer.INSTANCE);
        ticketTupeField.setRequired(true);
        form.add(ticketTupeField);

        DropDownChoice<TicketClass> ticketClassField = new DropDownChoice<>("ticketClass", Arrays.asList(TicketClass.values()), TicketClassChoiceRenderer.INSTANCE);
        ticketClassField.setRequired(true);
        form.add(ticketClassField);

		CheckBox priorityRegistrationField = new CheckBox("priorityRegistration");
		form.add(priorityRegistrationField);

		CheckBox prioritySeatsField = new CheckBox("prioritySeats");
		form.add(prioritySeatsField);
        
        
        TextField<Double> costsField = new TextField<>("costs");
		costsField.setRequired(true);
		form.add(costsField);
		
		CheckBox forBabyField = new CheckBox("forBaby");
		form.add(forBabyField);
 	
		
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (ticket.getId() == null) {
					ticketService.insert(ticket);
				} else {
					ticketService.update(ticket);
				}

				setResponsePage(new TicketsPage());
			}
		});

		add(new FeedbackPanel("feedback"));

    	
    }
}
