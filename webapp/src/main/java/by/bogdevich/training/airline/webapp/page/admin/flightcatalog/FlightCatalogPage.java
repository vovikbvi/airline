package by.bogdevich.training.airline.webapp.page.admin.flightcatalog;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.panel.FlightCatalogListPanel;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class FlightCatalogPage extends AbstractPage {

	public FlightCatalogPage() {
		super();
		add(new FlightCatalogListPanel("flight-catalog"));

	       add(new Link("create") {
	           @Override
	           public void onClick() {
	               setResponsePage(new FlightCatalogEditPage(new FlightCatalog()));
	           }
	       });

	}

}
