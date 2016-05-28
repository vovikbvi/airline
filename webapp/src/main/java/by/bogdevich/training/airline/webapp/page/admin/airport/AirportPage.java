package by.bogdevich.training.airline.webapp.page.admin.airport;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.airport.panel.AirportListPanel;
import by.bogdevich.training.airline.webapp.page.admin.city.panel.CityListPanel;
import by.bogdevich.training.airline.webapp.page.admin.country.panel.CountryListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flight.panel.FlightListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.panel.FlightCatalogListPanel;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.panel.ManufacturedListPanel;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class AirportPage extends AbstractPage {

	public AirportPage() {
		super();
		add(new AirportListPanel("airport"));

	       add(new Link("create") {
	           @Override
	           public void onClick() {
	               setResponsePage(new AirportEditPage(new Airport()));
	           }
	       });

	}

}
