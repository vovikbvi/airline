package by.bogdevich.training.airline.webapp.page.admin.airport;

import javax.inject.Inject;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.airport.panel.AirportListPanel;
import by.bogdevich.training.airline.webapp.page.admin.city.panel.CityListPanel;
import by.bogdevich.training.airline.webapp.page.admin.country.panel.CountryListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flight.panel.FlightListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.panel.FlightCatalogListPanel;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.panel.ManufacturedListPanel;

public class AirportPage extends AbstractPage {

	public AirportPage() {
		super();
		add(new AirportListPanel("airport"));

	}

}
