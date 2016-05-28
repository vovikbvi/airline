package by.bogdevich.training.airline.webapp.page.admin.flight;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.flight.panel.FlightListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.panel.FlightCatalogListPanel;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.panel.ManufacturedListPanel;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class FlightPage extends AbstractPage {

	public FlightPage() {
		super();
		add(new FlightListPanel("flight"));

	       add(new Link("create") {
	           @Override
	           public void onClick() {
	               setResponsePage(new FlightEditPage(new Flight()));
	           }
	       });

	}

}
