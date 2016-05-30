package by.bogdevich.training.airline.webapp.page.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;


import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.service.AirportService;
import by.bogdevich.training.airline.service.CityService;
import by.bogdevich.training.airline.webapp.common.AirportChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UsersPage;
import by.bogdevich.training.airline.webapp.page.login.LoginPage;
import by.bogdevich.training.airline.webapp.page.search.SearchPage;

public class HomePage extends AbstractPage {

	@Inject
	private AirportService airportService;
	
	@Inject 
	private CityService cityService;

	private FlightCatalog flightCatalog;
	
	private Airport airport;
	
	private String selected;

	public HomePage() {
		super();
	}

	protected void onInitialize() {
		super.onInitialize();
		
	      add(new Link("test") {
	            @Override
	            public void onClick() {
	                setResponsePage(new HomePage3());
	            }
	        });

		
		List<String> cityList = new ArrayList<String>();
		
		//List<City> listAirport= ;
		for (City city : new ArrayList<City>(cityService.getAll())) {
		cityList.add(city.getName());
	}
		//make Google selected by default
		
		
			add(new FeedbackPanel("feedback"));

			DropDownChoice<String> listSites = new DropDownChoice<String>(
					"sites", new PropertyModel<String>(this, "selected"), cityList);

			Form<?> form = new Form<Void>("form") {
				@Override
				protected void onSubmit() {

					info("Selected search engine : " + selected);

				}
			};

			add(form);
			form.add(listSites);

	
	}


	
}
