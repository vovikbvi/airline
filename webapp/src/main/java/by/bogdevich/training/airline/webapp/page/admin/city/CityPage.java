package by.bogdevich.training.airline.webapp.page.admin.city;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.city.panel.CityListPanel;
import by.bogdevich.training.airline.webapp.page.admin.country.panel.CountryListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flight.panel.FlightListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.panel.FlightCatalogListPanel;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.panel.ManufacturedListPanel;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class CityPage extends AbstractPage {

	public CityPage() {
		super();
		add(new CityListPanel("city"));

	       add(new Link("create") {
	           @Override
	           public void onClick() {
	               setResponsePage(new CityEditPage(new City()));
	           }
	       });

	}

}
