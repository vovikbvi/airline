package by.bogdevich.training.airline.webapp.page.admin.city;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.service.CityService;
import by.bogdevich.training.airline.service.CountryService;
import by.bogdevich.training.airline.webapp.common.CountryChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class CityEditPage extends AbstractPage {

	@Inject
	private CityService cityService;
	
	@Inject
	private CountryService countryService;

	private City city;

	public CityEditPage() {
		super();
	}

	public CityEditPage(City city) {
		super();
		this.city = city;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		
		Form form = new Form("form", new CompoundPropertyModel<City>(city));
		add(form);
  
    	TextField<String> nameField = new TextField<>("name");
		nameField.setRequired(true);
		form.add(nameField);
		
		ArrayList<Country> listCountry= new ArrayList<Country>(countryService.getAll());
        DropDownChoice<Country> countryField = new DropDownChoice<Country>("country", listCountry, CountryChoiceRenderer.INSTANCE);
        countryField.setRequired(true);
        form.add(countryField);
 	
       	TextField<Double> timeZoneField = new TextField<>("timezone");
   		timeZoneField.setRequired(true);
   		form.add(timeZoneField);

        
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (city.getId() == null) {
					cityService.insert(city);
				} else {
					cityService.update(city);
				}

				setResponsePage(new CityPage());
			}
		});

		add(new FeedbackPanel("feedback"));

		
	}
}
