package by.bogdevich.training.airline.webapp.page.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;


import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.service.AirportService;
import by.bogdevich.training.airline.service.CityService;
import by.bogdevich.training.airline.webapp.common.CityChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

public class HomePage3 extends AbstractPage {

	@Inject
	private AirportService airportService;
	
	@Inject
	private CityService cityService;

	private Airport airport;
	
	private City city;

	public HomePage3() {
		super();
		//this.airport = airportService.get((long) 365); 
	}

	protected void onInitialize() {
		super.onInitialize();
		
		Form form = new Form("form", new CompoundPropertyModel<City>(city));
		
		//Form form = new Form("form", new PropertyModel<City>(city, "name"));
		add(form);
 
		List<City> listAirport= new ArrayList<City>(cityService.getAll());
        DropDownChoice<City> airportField = new DropDownChoice<City>("name", listAirport, CityChoiceRenderer.INSTANCE);
        form.add(airportField);
 	
        
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				System.out.println("!!!!!!!!!!!!!"+ airportField.getModelObject().getName());
			
				//setResponsePage(new AirportEditPage(airport));
				
			}
		});

		add(new FeedbackPanel("feedback"));
	
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}


	
}
