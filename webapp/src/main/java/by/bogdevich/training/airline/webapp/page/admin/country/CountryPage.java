package by.bogdevich.training.airline.webapp.page.admin.country;

import javax.inject.Inject;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.country.panel.CountryListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flight.panel.FlightListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.panel.FlightCatalogListPanel;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.panel.ManufacturedListPanel;

public class CountryPage extends AbstractPage {

	public CountryPage() {
		super();
		add(new CountryListPanel("country"));

	}

}
