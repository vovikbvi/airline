package by.bogdevich.training.airline.webapp.page.admin.country;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.service.CountryService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class CountryEditPage extends AbstractPage {
	
	@Inject
	private CountryService countryService;
	
	private Country country;
	
    public CountryEditPage() {
        super();
    }

	public CountryEditPage(Country country) {
		super();
		this.country = country;
	}

    @Override
    protected void onInitialize() {
    	super.onInitialize();
    	
		Form form = new Form("form", new CompoundPropertyModel<Country>(country));
		add(form);
  
    	TextField<String> nameField = new TextField<>("name");
		nameField.setRequired(true);
		form.add(nameField);
		
 	
		
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (country.getId() == null) {
					countryService.insert(country);
				} else {
					countryService.update(country);
				}

				setResponsePage(new CountryPage());
			}
		});

		add(new FeedbackPanel("feedback"));

		
   	
    	
    }
}
