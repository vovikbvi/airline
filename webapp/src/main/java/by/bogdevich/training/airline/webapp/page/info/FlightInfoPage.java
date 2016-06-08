package by.bogdevich.training.airline.webapp.page.info;

import java.util.PropertyPermission;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.service.FlightService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.info.panel.FreeSeatsPanel;
import by.bogdevich.training.airline.webapp.page.info.panel.PlaneInfoPanel;


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
    	add(new PlaneInfoPanel("plane-info-panel", flight));
    	add(new FreeSeatsPanel("free-seats-panel", flight));
    	
    }
}
