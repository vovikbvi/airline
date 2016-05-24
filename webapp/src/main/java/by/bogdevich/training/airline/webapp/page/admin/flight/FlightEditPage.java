package by.bogdevich.training.airline.webapp.page.admin.flight;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.service.FlightService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

public class FlightEditPage extends AbstractPage {

	@Inject
	private FlightService flightService;

	private Flight flight;

	public FlightEditPage() {
		super();
	}

	public FlightEditPage(Flight flight) {
		super();
		this.flight = flight;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
	}
}
