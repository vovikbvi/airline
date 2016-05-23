package by.bogdevich.training.airline.webapp.page.admin.flightcatalog;

import javax.inject.Inject;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.panel.FlightCatalogListPanel;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.panel.ManufacturedListPanel;

public class FlightCatalogPage extends AbstractPage {

	public FlightCatalogPage() {
		super();
		add(new FlightCatalogListPanel("flight-catalog"));

	}

}
