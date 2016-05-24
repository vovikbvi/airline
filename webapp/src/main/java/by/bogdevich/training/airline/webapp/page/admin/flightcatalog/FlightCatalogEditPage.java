package by.bogdevich.training.airline.webapp.page.admin.flightcatalog;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.service.FlightCatalogService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

public class FlightCatalogEditPage extends AbstractPage {

	@Inject
	private FlightCatalogService flightCatalogService;

	private FlightCatalog flightCatalog;

	public FlightCatalogEditPage() {
		super();
	}

	public FlightCatalogEditPage(FlightCatalog flightCatalog) {
		super();
		this.flightCatalog = flightCatalog;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

	
	}
}
