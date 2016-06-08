package by.bogdevich.training.airline.webapp.page.flightinfo;

import java.util.PropertyPermission;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.service.FlightService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;


public class FlightInfoPage extends AbstractPage {

	@Inject
	private FlightService flightService;
	
	private Flight flight;
	
    public FlightInfoPage() {
        super();
    }

	public FlightInfoPage(Flight flight) {
		super();
		this.flight = flight;
	}

    @Override
    protected void onInitialize() {
    	super.onInitialize();
    	
    	flight = flightService.getFlieghtWithFetch(flight);
    	add(new Label("flight-start", flight.getFlightCatalog().getAirportStart().getName()));
    	add(new Label("flight-finish", flight.getFlightCatalog().getAirportFinish().getName()));
    	add(new Label("departure-time", flight.getDepartureTime()));
    	add(new Label("arrival-time", flight.getArrivalTime()));
    	add(new Label("registr-time", flight.getArrivalTime()));
    	add(new Label("bort-number", flight.getPlane().getBortNumber()));
    	add(new Label("model-plane", flight.getPlane().getModelPlane().getModel()));
    	add(new Label("free-seats-buisnes", flight.getPlane().getModelPlane().getColPassangersBuisnes()-flightService.countBesySeats(flight, TicketClass.BUSINES_CLASS)));
    	add(new Label("free-seats-firstclass", flight.getPlane().getModelPlane().getColPassangersFirstclass()-flightService.countBesySeats(flight, TicketClass.FIRST_CLASS)));
    	add(new Label("free-seats-economy", flight.getPlane().getModelPlane().getColPassangersEconomy()-flightService.countBesySeats(flight, TicketClass.ECONOMY)));
    	
    	
    	
    	
    }
}
