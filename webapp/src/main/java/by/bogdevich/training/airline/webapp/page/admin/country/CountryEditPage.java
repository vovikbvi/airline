package by.bogdevich.training.airline.webapp.page.admin.country;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.service.CountryService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;



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
    	
    	
    	
    }
}
