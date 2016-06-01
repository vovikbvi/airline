package by.bogdevich.training.airline.webapp.page.admin.flightcatalog;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.service.AirportService;
import by.bogdevich.training.airline.service.FlightCatalogService;
import by.bogdevich.training.airline.webapp.common.renderer.AirportChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class FlightCatalogEditPage extends AbstractPage {

	@Inject
	private FlightCatalogService flightCatalogService;
	
	@Inject
	private AirportService airportService;

	private FlightCatalog flightCatalog;

	public FlightCatalogEditPage() {
		super();
	}

	public FlightCatalogEditPage(FlightCatalog flightCatalog) {
		super();
		this.flightCatalog = flightCatalog;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form form = new Form("form", new CompoundPropertyModel<FlightCatalog>(flightCatalog));
		add(form);
  
		ArrayList<Airport> listAirport= new ArrayList<Airport>(airportService.getAll());
        DropDownChoice<Airport> airportStartField = new DropDownChoice<Airport>("airportStart", listAirport, AirportChoiceRenderer.INSTANCE);
        airportStartField.setRequired(true);
        form.add(airportStartField);
		
        DropDownChoice<Airport> airportFinishField = new DropDownChoice<Airport>("airportFinish", listAirport, AirportChoiceRenderer.INSTANCE);
        airportFinishField.setRequired(true);
        form.add(airportFinishField);
        
	
		TextField<Integer> distanceField = new TextField<>("distance");
		distanceField.setRequired(true);
		form.add(distanceField);
      
        
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (flightCatalog.getId() == null) {
					flightCatalogService.insert(flightCatalog);
				} else {
					flightCatalogService.update(flightCatalog);
				}

				setResponsePage(new FlightCatalogPage());
			}
		});

		add(new FeedbackPanel("feedback"));

	}
}
