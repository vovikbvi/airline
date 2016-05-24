package by.bogdevich.training.airline.webapp.page.admin.airport;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.service.AirportService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

public class AirportEditPage extends AbstractPage {

	@Inject
	private AirportService airportService;

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
		
		
	}
}
