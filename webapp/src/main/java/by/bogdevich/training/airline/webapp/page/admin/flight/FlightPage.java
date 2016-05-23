package by.bogdevich.training.airline.webapp.page.admin.flight;

import javax.inject.Inject;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.flight.panel.FlightListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.panel.FlightCatalogListPanel;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.panel.ManufacturedListPanel;

public class FlightPage extends AbstractPage {

	public FlightPage() {
		super();
		add(new FlightListPanel("flight"));

	}

}
