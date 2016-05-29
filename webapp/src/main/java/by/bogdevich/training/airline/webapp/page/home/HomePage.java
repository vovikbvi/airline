package by.bogdevich.training.airline.webapp.page.home;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.service.AirportService;
import by.bogdevich.training.airline.webapp.common.AirportChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.FlightCatalogPage;
import by.bogdevich.training.airline.webapp.page.login.LoginPage;
import by.bogdevich.training.airline.webapp.page.search.SearchPage;

public class HomePage extends AbstractPage {

	@Inject
	private AirportService airportService;

	private FlightCatalog flightCatalog;

	public HomePage() {
		super();
	}

	protected void onInitialize() {
		super.onInitialize();
		
			add(new Link("linkregistr") {
			@Override
			public void onClick() {
				setResponsePage(new LoginPage());
			}
		});

		add(new Link("linksearch") {
			@Override
			public void onClick() {
				setResponsePage(new SearchPage());
			}
		});

		Form form = new Form("form");
		add(form);

		ArrayList<Airport> listAirport = new ArrayList<Airport>(airportService.getAll());
		DropDownChoice<Airport> airportStartField = new DropDownChoice<Airport>("airportStart", listAirport, AirportChoiceRenderer.INSTANCE);
		form.add(airportStartField);

		
		
		
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				System.out.println(airportStartField.getValue());
				
			}
		});
	


		
	};
}
