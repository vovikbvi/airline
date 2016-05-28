package by.bogdevich.training.airline.webapp.page.admin.airport;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.service.AirportService;
import by.bogdevich.training.airline.service.CityService;
import by.bogdevich.training.airline.webapp.common.CityChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.ClassWeightChoiceRenderer;
import by.bogdevich.training.airline.webapp.common.CountryChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.city.CityPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class AirportEditPage extends AbstractPage {

	@Inject
	private AirportService airportService;
	
	@Inject
	private CityService cityService;

	private Airport airport;

	public AirportEditPage() {
		super();
	}

	public AirportEditPage(Airport airport) {
		super();
		this.airport = airport;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		Form form = new Form("form", new CompoundPropertyModel<Airport>(airport));
		add(form);
 
    	TextField<String> nameField = new TextField<>("name");
		nameField.setRequired(true);
		form.add(nameField);

		
    	TextField<String> codeIataField = new TextField<>("codeIata");
		codeIataField.setRequired(true);
		form.add(codeIataField);
		
		TextField<String> codeIcaoField = new TextField<>("codeIcao");
		form.add(codeIcaoField);
		
		ArrayList<City> listCity= new ArrayList<City>(cityService.getAll());
        DropDownChoice<City> cityField = new DropDownChoice<City>("city", listCity, CityChoiceRenderer.INSTANCE);
        cityField.setRequired(true);
        form.add(cityField);
 	
        DropDownChoice<ClassWeight> classWeightField = new DropDownChoice<>("classWeight", Arrays.asList(ClassWeight.values()), ClassWeightChoiceRenderer.INSTANCE);
        classWeightField.setRequired(true);
        form.add(classWeightField);

        
       	TextField<Double> coordinatesXField = new TextField<>("coordinatesX");
   		form.add(coordinatesXField);

   		TextField<Double> coordinatesYField = new TextField<>("coordinatesY");
   		form.add(coordinatesYField);
   		
        
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (airport.getId() == null) {
					airportService.insert(airport);
				} else {
					airportService.update(airport);
				}

				setResponsePage(new AirportPage());
			}
		});

		add(new FeedbackPanel("feedback"));


	}
}
