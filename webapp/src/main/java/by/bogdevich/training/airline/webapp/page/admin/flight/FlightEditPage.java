package by.bogdevich.training.airline.webapp.page.admin.flight;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.extensions.yui.calendar.DateTimeField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.service.FlightCatalogService;
import by.bogdevich.training.airline.service.FlightService;
import by.bogdevich.training.airline.service.PlaneService;
import by.bogdevich.training.airline.webapp.common.renderer.FlightCatalogChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.renderer.PlaneChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class FlightEditPage extends AbstractPage {

	@Inject
	private FlightService flightService;
	
	@Inject
	private FlightCatalogService flightCatalogService;
	
	@Inject
	private PlaneService planeService;

	private Flight flight;

	public FlightEditPage() {
		super();
	}

	public FlightEditPage(Flight flight) {
		super();
		this.flight = flight;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Form form = new Form("form", new CompoundPropertyModel<Flight>(flight));
		add(form);
  
		ArrayList<FlightCatalog> listFlightCatalog= new ArrayList<FlightCatalog>(flightCatalogService.getAll());
        DropDownChoice<FlightCatalog> flightField = new DropDownChoice<FlightCatalog>("flightCatalog", listFlightCatalog, FlightCatalogChoiceRenderer.INSTANCE);
        flightField.setRequired(true);
        form.add(flightField);
		
        DateTimeField registrTimeField = new DateTimeField("registrTime");
        registrTimeField.setRequired(true);
		form.add(registrTimeField);

		DateTimeField departureTimeField = new DateTimeField("departureTime");
		departureTimeField.setRequired(true);
		form.add(departureTimeField);
		
		DateTimeField arrivalTimeField = new DateTimeField("arrivalTime");
		arrivalTimeField.setRequired(true);
		form.add(arrivalTimeField);
		
		ArrayList<Plane> listPlane= new ArrayList<Plane>(planeService.getAll());
        DropDownChoice<Plane> planeField = new DropDownChoice<Plane>("plane", listPlane, PlaneChoiceRenderer.INSTANCE);
        planeField.setRequired(true);
        form.add(planeField);

		DateTextField startSaleTicketField = new DateTextField("startSaleTicket", "dd-MM-yyyy");
		startSaleTicketField.add(new DatePicker());
		startSaleTicketField.setRequired(true);
		form.add(startSaleTicketField);
		
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (flight.getId() == null) {
					flightService.insert(flight);
				} else {
					flightService.update(flight);
				}

				setResponsePage(new FlightPage());
			}
		});

		add(new FeedbackPanel("feedback"));
	
	}
}
